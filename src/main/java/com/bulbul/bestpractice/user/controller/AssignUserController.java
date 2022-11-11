package com.bulbul.bestpractice.user.controller;

import com.bulbul.bestpractice.user.entity.AssignUser;
import com.bulbul.bestpractice.common.generic.controller.AbstractSearchController;
import com.bulbul.bestpractice.common.generic.payload.seatch.IdQuerySearchDto;
import com.bulbul.bestpractice.user.dto.request.AssignUserDto;
import com.bulbul.bestpractice.user.service.AssignUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assign-user")
public class AssignUserController extends AbstractSearchController<AssignUser, AssignUserDto, IdQuerySearchDto> {

    public AssignUserController(AssignUserService iSearchService) {
        super(iSearchService);
    }
}
