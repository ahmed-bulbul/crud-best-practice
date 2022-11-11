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
public class UserProfileDto implements IDto {
    private String firstName;
    private String lastName;
    private String address;
    private String contactNumber;
}