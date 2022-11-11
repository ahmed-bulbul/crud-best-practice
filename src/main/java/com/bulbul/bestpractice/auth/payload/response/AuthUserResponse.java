package com.bulbul.bestpractice.auth.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthUserResponse {
    private Long userId;
    private String userName;
    private Set<AuthRoleResponse> assignRoles;
}
