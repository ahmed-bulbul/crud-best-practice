package com.bulbul.bestpractice.user.service;

import com.bulbul.bestpractice.common.generic.payload.response.PageData;
import com.bulbul.bestpractice.user.dto.projection.UserProjection;
import com.bulbul.bestpractice.user.dto.response.AssignUserResponse;
import com.bulbul.bestpractice.user.entity.AssignUser;
import com.bulbul.bestpractice.user.entity.User;
import com.bulbul.bestpractice.user.entity.UserProfile;
import com.bulbul.bestpractice.user.repository.UserRepository;
import com.bulbul.bestpractice.common.generic.payload.seatch.IdQuerySearchDto;
import com.bulbul.bestpractice.common.generic.service.AbstractSearchService;
import com.bulbul.bestpractice.common.generic.specification.CustomSpecification;
import com.bulbul.bestpractice.user.dto.request.UserDto;
import com.bulbul.bestpractice.user.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService extends AbstractSearchService<User, UserDto, IdQuerySearchDto> {

    private static final String IS_ACTIVE ="isActive";
    private static final String ID = "id";
    private final UserRepository userRepository;
    private final UserProfileService userProfileService;

    public UserService(UserRepository userRepository, UserProfileService userProfileService) {
        super(userRepository);
        this.userRepository = userRepository;
        this.userProfileService = userProfileService;
    }

//    @Override
//    public PageData getAll(Boolean isActive, Pageable pageable) {
//        Page<User> pagedData = userRepository.findAllByIsActive(isActive, pageable);
//        List<AssignUserResponse> models = facadeService.getResponseList(pagedData.getContent());
//
//        return PageData.builder()
//                .model(models)
//                .totalPages(pagedData.getTotalPages())
//                .totalElements(pagedData.getTotalElements())
//                .currentPage(pageable.getPageNumber() + 1)
//                .build();
//    }


    @Override
    protected Specification<User> buildSpecification(IdQuerySearchDto searchDto) {
        CustomSpecification<User> customSpecification = new CustomSpecification<>();
        return Specification.where(customSpecification.equalSpecificationAtRoot(searchDto.getId(),ID )
                .and(customSpecification.equalSpecificationAtRoot(searchDto.getIsActive(), IS_ACTIVE)));
    }

    @Override
    protected User convertToEntity(UserDto dto) {
        UserProfile userProfile = userProfileService.findById(dto.getUserProfileId());
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .userProfile(userProfile)
                .build();
    }

    @Override
    protected UserResponse convertToResponseDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .userProfileId(user.getUserProfile().getId())
                .userProfileName(user.getUserProfile().getFirstName())
                .build();
    }

    @Override
    protected User updateEntity(UserDto dto, User entity) {
        return entity;
    }

    public Set<UserProjection> getUserByIds(Set<Long> userIds, Boolean status){
        return userRepository.findByIdInAndIsActive(userIds, status);
    }

}