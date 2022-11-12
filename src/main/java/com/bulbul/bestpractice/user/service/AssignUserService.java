package com.bulbul.bestpractice.user.service;

import com.bulbul.bestpractice.user.dto.response.AssignUserResponse;
import com.bulbul.bestpractice.user.entity.AssignUser;
import com.bulbul.bestpractice.user.entity.User;
import com.bulbul.bestpractice.user.repository.AssignUserRepository;
import com.bulbul.bestpractice.common.generic.payload.response.PageData;
import com.bulbul.bestpractice.common.generic.payload.seatch.IdQuerySearchDto;
import com.bulbul.bestpractice.common.generic.service.AbstractSearchService;
import com.bulbul.bestpractice.user.dto.request.AssignUserDto;
import com.bulbul.bestpractice.user.dto.response.CustomUserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignUserService extends AbstractSearchService<AssignUser, AssignUserDto, IdQuerySearchDto> {
    private final AssignUserRepository assignUserRepository;
    private final AssignUserFacadeService facadeService;

    public AssignUserService(AssignUserRepository assignUserRepository, AssignUserFacadeService facadeService) {
        super(assignUserRepository);
        this.assignUserRepository = assignUserRepository;
        this.facadeService = facadeService;
    }

    @Override
    public PageData getAll(Boolean isActive, Pageable pageable) {
        Page<AssignUser> pagedData = assignUserRepository.findAllByIsActive(isActive, pageable);
        List<AssignUserResponse> models = facadeService.getResponseList(pagedData.getContent());

        return PageData.builder()
                .model(models)
                .totalPages(pagedData.getTotalPages())
                .totalElements(pagedData.getTotalElements())
                .currentPage(pageable.getPageNumber() + 1)
                .build();
    }

    @Override
    public PageData search(IdQuerySearchDto searchDto, Pageable pageable) {

        Page<AssignUser> pagedData = assignUserRepository.findAll(this.buildSpecification(searchDto), pageable);
        List<AssignUserResponse> models = facadeService.getResponseList(pagedData.getContent());

        return PageData.builder()
                .model(models)
                .totalPages(pagedData.getTotalPages())
                .totalElements(pagedData.getTotalElements())
                .currentPage(pageable.getPageNumber() + 1)
                .build();
    }

    @Override
    protected Specification<AssignUser> buildSpecification(IdQuerySearchDto searchDto) {
        return null;
    }

    @Override
    protected AssignUserResponse convertToResponseDto(AssignUser assignUser) {
        CustomUserResponse userResponse = CustomUserResponse.builder()
                .userId(assignUser.getUserId())
                .userCode(assignUser.getUser().getCode())
                .userProfileId(assignUser.getUser().getUserProfileId())
                .firstName(assignUser.getUser().getUserProfile().getFirstName())
                .lastName(assignUser.getUser().getUserProfile().getLastName())
                .contactNo(assignUser.getUser().getUserProfile().getContactNumber())
                .build();
        return AssignUserResponse.builder()
                .id(assignUser.getId())
                .userInfo(userResponse)
                .build();
    }

    @Override
    protected AssignUser convertToEntity(AssignUserDto assignUserDto) {
        User user = facadeService.assignUserWithRole(assignUserDto.getUserId(), assignUserDto.getRoleIds());
        return AssignUser.builder()
                .user(user)
                .build();
    }

    @Override
    protected AssignUser updateEntity(AssignUserDto dto, AssignUser entity) {
        facadeService.updateUserWithOrWithoutRole(entity, dto.getUserId(), dto.getRoleIds());
        return entity;
    }

}
