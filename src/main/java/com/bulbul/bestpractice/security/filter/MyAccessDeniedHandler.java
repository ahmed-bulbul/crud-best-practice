package com.bulbul.bestpractice.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        writeCustomResponse(response,request);
    }

    private void writeCustomResponse(HttpServletResponse response,HttpServletRequest request) {
        if (!response.isCommitted()) {
            try {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);

                final Map<String, Object> body = new HashMap<>();
                body.put("status", HttpServletResponse.SC_FORBIDDEN);
                body.put("error", "Forbidden");
                body.put("message", "You don't have permission to access this resource");
                body.put("path", request.getServletPath());

                final ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(response.getOutputStream(), body);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}