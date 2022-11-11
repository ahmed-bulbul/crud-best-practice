package com.bulbul.bestpractice.user.controller;

import com.bulbul.bestpractice.user.entity.UserProfile;
import com.bulbul.bestpractice.user.service.UserProfileService;
import com.bulbul.bestpractice.common.generic.controller.AbstractSearchController;
import com.bulbul.bestpractice.common.generic.payload.seatch.IdQuerySearchDto;
import com.bulbul.bestpractice.user.dto.request.UserProfileDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-profile")
public class UserProfileController extends AbstractSearchController<UserProfile, UserProfileDto, IdQuerySearchDto> {

    public UserProfileController(UserProfileService userProfileService) {
        super(userProfileService);
    }
}