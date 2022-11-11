package com.bulbul.bestpractice.security.filter;

import com.bulbul.bestpractice.utils.UserUtil;
import com.bulbul.bestpractice.common.constant.ApplicationConstant;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Slf4j
public class MyAccessDecisionManager implements AccessDecisionManager {



    @SneakyThrows
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

        log.info("configAttributes : {}", configAttributes);
        // call auth check service
        if( hasAuthorization( authentication, configAttributes) ) {
            log.info("So You are Authorized...");
        } else {
            log.info("Not Authorized...");
            throw new AccessDeniedException("Not Authorized...");
        }

    }


    public boolean hasAuthorization( Authentication authentication, Collection<ConfigAttribute> configAttributes ) {
        for (ConfigAttribute ca : configAttributes) {
            String needRole = ca.getAttribute();
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                log.info("needRole : {}", needRole);
                if (ga.getAuthority().equals(needRole)) {
                    return true;
                }else if(UserUtil.getLoginUserAuthorities().contains(ApplicationConstant.ROLE_ADMIN)){
                    log.info("needRole : {}", needRole);
                    log.info("Hey! You are  admin. You can access all resources");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }



}