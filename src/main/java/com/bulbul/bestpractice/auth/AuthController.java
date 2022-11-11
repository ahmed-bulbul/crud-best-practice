package com.bulbul.bestpractice.auth;


import com.bulbul.bestpractice.auth.payload.response.AuthRoleResponse;
import com.bulbul.bestpractice.auth.payload.response.AuthUserResponse;
import com.bulbul.bestpractice.user.entity.User;
import com.bulbul.bestpractice.user.repository.UserRepository;
import com.bulbul.bestpractice.user.service.UserService;
import com.bulbul.bestpractice.rolemanagement.entity.Role;
import com.bulbul.bestpractice.security.jwt.config.JwtUtils;
import com.bulbul.bestpractice.security.jwt.payload.request.LoginRequest;
import com.bulbul.bestpractice.security.jwt.payload.response.JwtResponse;
import com.bulbul.bestpractice.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User optionalUser = userRepository.findByUsername(userDetails.getUsername()).orElse(null);

            if(optionalUser!=null){
                AuthUserResponse userResponse = new AuthUserResponse();
                userResponse.setUserName(optionalUser.getUsername());
                userResponse.setUserId(optionalUser.getId());
                Set<Role> assignedRoles = optionalUser.getRoles();
                Set<AuthRoleResponse> roleResponseSet = assignedRoles.stream().map(role -> AuthRoleResponse.builder()
                        .id(role.getId()).name(role.getName()).build()).collect(Collectors.toSet());
                userResponse.setAssignRoles(roleResponseSet);


                return ResponseEntity.ok(new JwtResponse("Login successfully", true, userResponse, jwt, "Bearer"));
            }

        }catch (Exception e){
            log.error("Error : {} "+e.getMessage());
        }
        return null;

    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    //access denied
    @GetMapping("/denied")
    public String accessDenied(){
        throw new AccessDeniedException("Access Denied");
    }
}
