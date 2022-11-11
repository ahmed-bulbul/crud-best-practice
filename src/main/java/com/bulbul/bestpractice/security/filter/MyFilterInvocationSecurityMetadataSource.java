package com.bulbul.bestpractice.security.filter;


import com.bulbul.bestpractice.security.helper.SpringSecurityHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

@Component
@Slf4j
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private SpringSecurityHelper securityHelper;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequest().getRequestURI();
        log.info("url: {}", url);
        if (
            //For Multimedia File Upload
                url.contains("/multimedia/")


                        // For Auth Controller
                        || url.contains("/api/v1/auth/login")
                        || url.contains("/api/v1/auth/register")
                        || url.contains("/api/v1/auth/forgot-password")
                        || url.contains("/api/v1/auth/reset-password")
                        || url.contains("/api/v1/auth/change-password")
                        || url.contains("/api/v1/auth/verify")
                        || url.contains("/api/v1/devTools/create")


                        //For Swagger UI
                        || url.contains("webjars")
                        || url.contains("swagger-resources")
                        || url.contains("/swagger-ui.html")
                        || url.contains("/favicon.ico")
                        || url.contains("/v2/api-docs")
                        || url.contains("/configuration/security")
                        || url.contains("/configuration/ui")
                        || url.contains("/v/api-docs")
                        || url.contains("/swagger-resources/configuration/ui")
                        || url.contains("/null/swagger-resources")
                        || url.contains("/null/configuration/security")
                        || url.contains("/null/swagger-resources/configuration/ui")


        ) {
            return Collections.emptyList();
        }

        List<String> attributes;

        log.info("Original URL : "+url);
        log.info("Original REQUEST "+fi.getHttpRequest().getMethod());


        String srcURL = url;
        String reqMethod=fi.getHttpRequest().getMethod();
        String lastIndexUriPath = url.substring(srcURL.lastIndexOf('/') + 1);

        if (!Pattern.matches("[a-zA-Z]+", lastIndexUriPath) && Pattern.matches("[0-9]+",
                lastIndexUriPath) && (fi.getHttpRequest().getMethod().equals("GET")
                || fi.getHttpRequest().getMethod().equals("PUT")
                || fi.getHttpRequest().getMethod().equals("POST")
                || fi.getHttpRequest().getMethod().equals("DELETE"))) {
            srcURL = srcURL.replaceAll("[0-9]", "");
        }

        if (contextPath.length() > 0) srcURL = srcURL.replaceAll(contextPath, "");

        log.info("srcUrl "+srcURL);

        attributes = securityHelper.getSecurityConfigAttributes(srcURL,reqMethod);
        log.info("attributes: {}", attributes);
        return SecurityConfig.createList(attributes.toArray(new String[0]));
    }


    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }


}
