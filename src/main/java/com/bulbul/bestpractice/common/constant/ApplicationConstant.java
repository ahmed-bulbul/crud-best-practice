package com.bulbul.bestpractice.common.constant;

import java.util.HashSet;
import java.util.Set;

public class ApplicationConstant {
    /**
     * trace id constant
     */
    public static final String TRACE_ID = "traceId";
    /**
     * errorCode json file name
     */
    public static final String ERROR_CODE_JSON_FILE = "error_code.json";
    /**
     * system error message
     */
    /**
     * Default page size while paginated query
     */
    public static final int DEFAULT_SIZE = 10;
    public static final String DEFAULT_SORT = "id";
    /**
     * Minimum character is 8, only alphabet & number. It also allows symbol. and within character & number & symbol,
     * at least use 2 item mandatory.
     */
    public static final String VALID_PASSWORD_REGEX = "^((((?=.*?[A-Z])|(?=.*?[a-z]))(?=.*?[0-9]))" +
            "|((?=.*?[0-9])(?=.*?[!#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]))|" +
            "((?=.*?[!#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~])((?=.*?[A-Z])|(?=.*?[a-z])))).{8,256}$";
    /**
     * Email validation regex
     */
    public static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    /**
     * Default page number.
     */
    public static final String DEFAULT_PAGE_NUMBER = "1";
    /**
     * Default page size.
     */
    public static final String DEFAULT_PAGE_SIZE = "20";
    /**
     * deletion message
     */
    public static final String EMPTY_STRING = "";
    public static final Long ANONYMOUS_USER = -1L;
    public static final String MESSAGE_SEPARATOR = "###";
    public static final Integer ZERO = 0;
    public static final Boolean STATUS_TRUE = Boolean.TRUE;
    public static final Boolean STATUS_FALSE = Boolean.FALSE;
    public static final String USER_NAME = "username";
    public static final String ENTITY_ID = "id";
    public static final String ACTIVE_FIELD = "isActive";
    public static final String TABLE_USER = "user";
    public static final String TABLE_ROLE = "role";
    public static final Set<Long> roles = new HashSet<>();
    public static final String PERMISSION_ROLE_ID = "roleId";
    public static final String ENTITY_NAME = "name";
    public static final String UNKNOWN_NAME = "Unknown Name";
    public static final Double DEFAULT_DOUBLE_ZERO = 0.0;
    public static final String PHONE_NUMBER_VALIDATION = "([0-9]{1,20})";
    /**
     * LIST first index!
     */
    public static final int FIRST_INDEX = 0;
    public static final String FARE_INFO_UNIQUE_CODE = "uniqueCode";
    public static final String ASSIGN_USER = "Assign User";
    /**
     * used for store user information
     */
    public static String SUCCESSFULLY_LOGOUT = "successfully logout";
    /**
     * Value One
     */

    /**
     * SYSTEM ROLE
    * */
    public static String ROLE_ADMIN = "ROLE_ADMIN";
    public static String ROLE_USER = "ROLE_USER";

    public static final String NUMBER_INVALID = "edcm1002";
    public static final String ENTITY_CODE = "code";

}
