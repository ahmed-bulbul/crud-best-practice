package com.bulbul.bestpractice.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AssignUserResponse {
    private Long id;
    private CustomUserResponse userInfo;
    private Long agencyId;
    private String agencyCode;
    private String agencyName;
    private Long fareInfoId;
    private String fareInfoCode;

}
