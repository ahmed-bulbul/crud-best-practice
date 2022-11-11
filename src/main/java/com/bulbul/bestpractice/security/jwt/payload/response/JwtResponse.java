package com.bulbul.bestpractice.security.jwt.payload.response;


import com.bulbul.bestpractice.auth.payload.response.AuthUserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String message;
    private boolean status;
    private AuthUserResponse user;
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private long expiresAt;

    public JwtResponse(String message, boolean status, AuthUserResponse user, String token, String bearer) {
        this.message = message;
        this.status = status;
        this.user = user;
        this.accessToken = token;
        this.tokenType = bearer;
    }
}
