package com.bulbul.bestpractice.user.controller;

import com.bulbul.bestpractice.user.entity.User;
import com.bulbul.bestpractice.user.service.UserService;
import com.bulbul.bestpractice.common.generic.controller.AbstractSearchController;
import com.bulbul.bestpractice.common.generic.payload.seatch.IdQuerySearchDto;
import com.bulbul.bestpractice.user.dto.request.UserDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractSearchController<User, UserDto, IdQuerySearchDto> {

    public UserController(UserService userService) {
        super(userService);
    }
}