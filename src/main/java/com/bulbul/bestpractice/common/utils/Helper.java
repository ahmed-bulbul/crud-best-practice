package com.bulbul.bestpractice.common.utils;


import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

import static com.bulbul.bestpractice.common.constant.ApplicationConstant.MESSAGE_SEPARATOR;


@Component
@Slf4j
public class Helper {
//    private static final Logger LOGGER = LoggerFactory.getLogger(Helper.class);
//
//    private final HttpServletRequest request;
//
//    @Autowired
//    public Helper(HttpServletRequest request) {
//        this.request = request;
//    }
//
//    public static Long getAuthUserId() {
//        Long userId = ApplicationConstant.ANONYMOUS_USER;
//        try {
//            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//            if (Objects.isNull(auth)) return null;
//            userId = ((UserDetailsImpl) auth.getPrincipal()).getId();
//        } catch (Exception e) {
//            LOGGER.error("Auth data could not be extracted: {}", e.getMessage());
//        }
//        return userId;
//    }
//
    public static String createDynamicCode(String errorCode, String... placeHolders) {
        StringBuilder builder = new StringBuilder(errorCode);
        if (Objects.isNull(placeHolders)) {
            return errorCode;
        }
        Arrays.stream(placeHolders).forEach(placeHolder -> builder.append(MESSAGE_SEPARATOR).append(placeHolder));
        return builder.toString();
    }
}
