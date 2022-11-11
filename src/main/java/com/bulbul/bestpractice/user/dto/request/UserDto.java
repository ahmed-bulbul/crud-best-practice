package com.bulbul.bestpractice.user.dto.request;

import com.bulbul.bestpractice.common.generic.payload.marker.IDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto implements IDto {
    private String username;
    private String password;
    private Long userProfileId;
}