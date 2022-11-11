package com.bulbul.bestpractice.security.helper;


import com.bulbul.bestpractice.common.constant.ApplicationConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * SpringSecurityHelper
 * @author BulbulAhmed
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class SpringSecurityHelper {

    public List<String> getSecurityConfigAttributes(String url,String reqMethod) {
        log.info("url is {} : "+url);
        log.info("method is : {} "+reqMethod);
        //here needs to check user or role permission for specific url

        ArrayList<String> attributes = new ArrayList<>();
        attributes.add(ApplicationConstant.ROLE_ADMIN);
        //attributes.add(ApplicationConstant.ROLE_USER);
        return attributes;
    }
}
