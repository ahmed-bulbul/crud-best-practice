package com.bulbul.bestpractice.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomUserResponse {
    private Long userProfileId;
    private Long userId;
    private String userCode;
    private String firstName;
    private String lastName;
    private String contactNo;

}
