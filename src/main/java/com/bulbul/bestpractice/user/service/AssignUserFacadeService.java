package com.bulbul.bestpractice.user.service;

import com.bulbul.bestpractice.user.dto.projection.UserProfileProjection;
import com.bulbul.bestpractice.user.dto.projection.UserProjection;
import com.bulbul.bestpractice.user.dto.response.AssignUserResponse;
import com.bulbul.bestpractice.user.entity.AssignUser;
import com.bulbul.bestpractice.user.entity.User;
import com.bulbul.bestpractice.common.constant.ApplicationConstant;
import com.bulbul.bestpractice.flightmanagement.dto.projection.AgencyProjection;
import com.bulbul.bestpractice.flightmanagement.dto.projection.FareInfoProjection;
import com.bulbul.bestpractice.flightmanagement.entity.Agency;
import com.bulbul.bestpractice.flightmanagement.entity.FareInfo;
import com.bulbul.bestpractice.flightmanagement.service.AgencyService;
import com.bulbul.bestpractice.flightmanagement.service.FareInfoService;
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
    private final FareInfoService fareInfoService;
    private final AgencyService agencyService;
    private final UserProfileService userProfileService;

    public AssignUserFacadeService(UserService userService, RoleService roleService, FareInfoService fareInfoService,
                                   AgencyService agencyService, UserProfileService userProfileService) {
        this.userService = userService;
        this.roleService = roleService;
        this.fareInfoService = fareInfoService;
        this.agencyService = agencyService;
        this.userProfileService = userProfileService;
    }

    public User assignUserWithRole(Long userId, Set<Long> roleIds) {
        Set<Role> roles = new HashSet<>(roleService.getAllByDomainIdIn(roleIds, ApplicationConstant.STATUS_TRUE));
        User user = userService.findById(userId);
        user.setRoles(roles);
        return user;
    }

    public Agency getAgencyById(Long agencyId) {
        return agencyService.findById(agencyId);
    }

    public FareInfo getFareInfoById(Long fareInfoId) {
        return fareInfoService.findById(fareInfoId);
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

        Set<Long> agencyIds = assignUserList.stream().map(AssignUser::getAgencyId).collect(Collectors.toSet());
        Map<Long, AgencyProjection> agencyMap = agencyService.getAgencyByIds(agencyIds, ApplicationConstant.STATUS_TRUE)
                .stream().collect(Collectors.toMap(AgencyProjection::getId, Function.identity()));
        Set<Long> fareIds = assignUserList.stream().map(AssignUser::getFareInfoId).collect(Collectors.toSet());
        Map<Long, FareInfoProjection> fareMap = fareInfoService.getAllFareByIds(fareIds, ApplicationConstant.STATUS_TRUE)
                .stream().collect(Collectors.toMap(FareInfoProjection::getId, Function.identity()));

        return assignUserList.stream().map(assignUser -> {
            UserProjection userProjection = userMap.get(assignUser.getUserId());
            return getResponse(userProjection, profileMap.get(userProjection.getUserProfileId()), agencyMap
                    .get(assignUser.getAgencyId()), fareMap.get(assignUser.getFareInfoId()), assignUser);
        }).collect(Collectors.toList());
    }

    public AssignUserResponse getResponse(UserProjection userProjection, UserProfileProjection userProfileProjection,
                                          AgencyProjection agencyProjection, FareInfoProjection fareInfoProjection,
                                          AssignUser assignUser) {
        AssignUserResponse response = new AssignUserResponse();
        response.setId(assignUser.getId());
        response.setUserInfo(getUserResponse(userProjection, userProfileProjection));
        if (Objects.nonNull(agencyProjection)) {
            response.setAgencyCode(agencyProjection.getCode());
            response.setAgencyId(agencyProjection.getId());
            response.setAgencyName(agencyProjection.getName());
        }
        if (Objects.nonNull(fareInfoProjection)) {
            response.setFareInfoCode(fareInfoProjection.getUniqueCode());
            response.setFareInfoId(fareInfoProjection.getId());
        }

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
