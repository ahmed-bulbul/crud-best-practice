package com.bulbul.bestpractice.rolemanagement.controller;

import com.bulbul.bestpractice.rolemanagement.dto.request.RoleDto;
import com.bulbul.bestpractice.rolemanagement.service.RoleService;
import com.bulbul.bestpractice.common.generic.controller.AbstractSearchController;
import com.bulbul.bestpractice.common.generic.payload.seatch.IdQuerySearchDto;
import com.bulbul.bestpractice.rolemanagement.entity.Role;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController extends AbstractSearchController<Role, RoleDto, IdQuerySearchDto> {

    public RoleController(RoleService roleService) {
        super(roleService);
    }
}