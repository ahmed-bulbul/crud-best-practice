package com.bulbul.bestpractice.user.service;

import com.bulbul.bestpractice.user.dto.projection.UserProfileProjection;
import com.bulbul.bestpractice.user.dto.projection.UserProjection;
import com.bulbul.bestpractice.user.dto.response.AssignUserResponse;
import com.bulbul.bestpractice.user.entity.AssignUser;
import com.bulbul.bestpractice.user.entity.User;
import com.bulbul.bestpractice.common.constant.ApplicationConstant;
import com.bulbul.bestpractice.rolemanagement.entity.Role;
import com.bulbul.bestpractice.rolemanagement.service.RoleService;
import com.bulbul.bestpractice.user.dto.response.CustomUserResponse;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AssignUserFacadeService {
    private final UserService userService;
    private final RoleService roleService;

    private final UserProfileService userProfileService;

    public AssignUserFacadeService(UserService userService, RoleService roleService,
                                  UserProfileService userProfileService) {
        this.userService = userService;
        this.roleService = roleService;
        this.userProfileService = userProfileService;
    }

    public User assignUserWithRole(Long userId, Set<Long> roleIds) {
        Set<Role> roles = new HashSet<>(roleService.getAllByDomainIdIn(roleIds, ApplicationConstant.STATUS_TRUE));
        User user = userService.findById(userId);
        user.setRoles(roles);
        return user;
    }


    public void updateUserWithOrWithoutRole(AssignUser entity, Long userId, Set<Long> roleIds) {
        Long entityUserId = entity.getUserId();
        User user;
        if (Objects.equals(entityUserId, userId)) {

            user = userService.findById(entityUserId);
            Map<Long, Role> roleMap = user.getRoles().stream().collect(Collectors.toMap(Role::getId, Function.identity()));
            List<Long> roleMapKeys = new ArrayList<>(roleMap.keySet());

            for (Long roleMapKey : roleMapKeys) {
                if (!roleIds.contains(roleMapKey)) {
                    Role role = roleMap.get(roleMapKey);
                    roleMap.remove(roleMapKey, role);
                } else {
                    roleIds.remove(roleMapKey);
                }
            }

            Set<Role> newRoles = new HashSet<>(roleService.getAllByDomainIdIn(roleIds, ApplicationConstant.STATUS_TRUE));
            newRoles.addAll(roleMap.values());

            user.setRoles(newRoles);
        } else {
            user = assignUserWithRole(userId, roleIds);
        }
        entity.setUser(user);
    }


    public List<AssignUserResponse> getResponseList(List<AssignUser> assignUserList) {

        Set<Long> userIds = assignUserList.stream().map(AssignUser::getUserId).collect(Collectors.toSet());
        Map<Long, UserProjection> userMap = userService.getUserByIds(userIds, ApplicationConstant.STATUS_TRUE).stream()
                .collect(Collectors.toMap(UserProjection::getId, Function.identity()));


        Set<Long> profileIds = userMap.values().stream().map(UserProjection::getUserProfileId)
                .collect(Collectors.toSet());
        Map<Long, UserProfileProjection> profileMap = userProfileService.getUserProfileByIdIn(profileIds,
                ApplicationConstant.STATUS_TRUE).stream().collect(Collectors.toMap(UserProfileProjection::getId,
                Function.identity()));


        return assignUserList.stream().map(assignUser -> {
            UserProjection userProjection = userMap.get(assignUser.getUserId());
            return getResponse(userProjection, profileMap.get(userProjection.getUserProfileId()), assignUser);
        }).collect(Collectors.toList());
    }

    public AssignUserResponse getResponse(UserProjection userProjection, UserProfileProjection userProfileProjection,
                                          AssignUser assignUser) {
        AssignUserResponse response = new AssignUserResponse();
        response.setId(assignUser.getId());
        response.setUserInfo(getUserResponse(userProjection, userProfileProjection));


        return response;

    }

    private CustomUserResponse getUserResponse(UserProjection userProjection, UserProfileProjection userProfileProjection) {
        CustomUserResponse response = new CustomUserResponse();
        if (Objects.nonNull(userProjection)) {
            response.setUserId(userProjection.getId());
            response.setUserCode(userProjection.getCode());
            response.setUserProfileId(userProjection.getUserProfileId());
        }
        if (Objects.nonNull(userProfileProjection)) {
            response.setLastName(userProfileProjection.getLastName());
            response.setFirstName(userProfileProjection.getFirstName());
            response.setContactNo(userProfileProjection.getContactNumber());
        }

        return response;
    }
}
