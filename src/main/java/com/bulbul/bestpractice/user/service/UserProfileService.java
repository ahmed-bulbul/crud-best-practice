package com.bulbul.bestpractice.user.service;

import com.bulbul.bestpractice.user.repository.UserProfileRepository;
import com.bulbul.bestpractice.common.generic.payload.seatch.IdQuerySearchDto;
import com.bulbul.bestpractice.common.generic.service.AbstractSearchService;
import com.bulbul.bestpractice.common.generic.specification.CustomSpecification;
import com.bulbul.bestpractice.user.dto.projection.UserProfileProjection;
import com.bulbul.bestpractice.user.dto.request.UserProfileDto;
import com.bulbul.bestpractice.user.dto.response.UserProfileResponse;
import com.bulbul.bestpractice.user.entity.UserProfile;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserProfileService extends AbstractSearchService<UserProfile, UserProfileDto, IdQuerySearchDto> {
    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    protected Specification<UserProfile> buildSpecification(IdQuerySearchDto searchDto) {
        CustomSpecification<UserProfile> customSpecification = new CustomSpecification<>();
        return null;
    }

    @Override
    protected UserProfile convertToEntity(UserProfileDto dto) {
        return UserProfile.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .address(dto.getAddress())
                .contactNumber(dto.getContactNumber())
                .build();
    }

    @Override
    protected UserProfileResponse convertToResponseDto(UserProfile userProfile) {
        return UserProfileResponse.builder()
                .id(userProfile.getId())
                .firstName(userProfile.getFirstName())
                .lastName(userProfile.getLastName())
                .address(userProfile.getAddress())
                .contactNumber(userProfile.getContactNumber())
                .build();
    }

    @Override
    protected UserProfile updateEntity(UserProfileDto dto, UserProfile entity) {
        return entity;
    }

    public Set<UserProfileProjection> getUserProfileByIdIn(Set<Long> userProfileId, Boolean activeStatus) {
        return userProfileRepository.findAlByIdInAndIsActive(userProfileId, activeStatus);
    }
}