package com.bulbul.bestpractice.rolemanagement.service;

import com.bulbul.bestpractice.rolemanagement.dto.request.RoleDto;
import com.bulbul.bestpractice.rolemanagement.repository.RoleRepository;
import com.bulbul.bestpractice.common.generic.payload.seatch.IdQuerySearchDto;
import com.bulbul.bestpractice.common.generic.service.AbstractSearchService;
import com.bulbul.bestpractice.common.generic.specification.CustomSpecification;
import com.bulbul.bestpractice.rolemanagement.dto.response.RoleResponse;
import com.bulbul.bestpractice.rolemanagement.entity.Role;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends AbstractSearchService<Role, RoleDto, IdQuerySearchDto> {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

    @Override
    protected Specification<Role> buildSpecification(IdQuerySearchDto searchDto) {
        CustomSpecification<Role> customSpecification = new CustomSpecification<>();
        return null;
    }

    @Override
    protected Role convertToEntity(RoleDto dto) {
        return Role.builder()
                .name(dto.getName())
                .build();
    }

    @Override
    protected RoleResponse convertToResponseDto(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    @Override
    protected Role updateEntity(RoleDto dto, Role entity) {
        entity.setName(dto.getName());
        return entity;
    }

}