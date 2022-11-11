package com.bulbul.bestpractice.common.constant;

public class ErrorId {
    public static final String DATA_NOT_SAVED = "stores1002";
    /**
     * Stores not found
     */
    public static final String SYSTEM_ERROR = "a4000";
    /**
     * User not exists.
     */
    public static final String USER_NOT_EXISTS = "a40001";
    /**
     * Role not exists.
     */
    public static final String ROLE_NOT_EXISTS = "a40002";
    /**
     * Id is required.
     */
    public static final String ID_IS_REQUIRED = "a40003";
    /**
     * User exists with this name.
     */
    public static final String USER_EXISTS_WITH_THIS_NAME = "a40004";
    /**
     * Role is required.
     */
    public static final String ROLE_IS_REQUIRED = "a40005";
    /**
     * Login is required.
     */
    public static final String LOGIN_IS_REQUIRED = "a40006";
    /**
     * Password is required.
     */
    public static final String PASSWORD_IS_REQUIRED = "a40007";
    /**
     * login size should be in between 3 and 20.
     */
    public static final String INVALID_LOGIN_SIZE = "a40008";
    /**
     * invalid data format exception.
     */
    public static final String INVALID_DATA_FORMAT_EXCEPTION = "a5000";
    /**
     * Invalid jwt token.
     */
    public static final String INVALID_JWT_TOKEN = "a40010";

    /**
     * Role Name already exists
     */
    public static final String ROLE_NAME_ALREADY_EXISTS = "role02";
    /**
     * Role duplication has failed
     */
    /**
     * User has no role
     */
    public static final String INVALID_USER = "u1001";
    /**
     * Invalid password pattern.
     */
    public static final String INVALID_PASSWORD_PATTERN = "a40011";
    /**
     * Invalid email pattern.
     */
    public static final String INVALID_EMAIL_PATTERN = "a40012";
    /**
     * Password mismatch with confirmation password.
     */
    public static final String PASSWORD_MISMATCH_WITH_CONFIRMATION_PASSWORD = "a40013";
    /**
     * Access right id is required
     */
    public static final String ACCESS_RIGHT_ID_REQUIRED = "ar1001";
    /**
     * Failed to update role access rights
     */
    public static final String FAILED_TO_UPDATE_ROLE_ACCESS_RIGHTS = "r1001";
    /**
     * Failed to save user.
     */
    public static final String FAILED_TO_SAVE_USER = "a40014";
    /**
     * Set of id can't be null or empty
     */
    public static final String FILE_PATH_NOT_FOUND = "fp10001";
    /**
     * Invalid access permission
     */

    /**
     * Company name already exist.
     */
    public static final String DATA_NOT_FOUND = "common01";
    public static final String ONLY_TOGGLE_VALUE_ACCEPTED = "common02";
    /**
     * Module name already exist.
     */
    /**
     * Submodule Item ID is required
     */
    public static final String SUBMODULE_ITEM_ID_REQUIRED = "nts1002";

    public static final String COMMON_FIELD_ERROR = "field01";


    /**
     * Refresh token was expired. Please make a new signin request.
     */
    public static final String REFRESH_TOKEN_WAS_EXPIRED = "a50001";
    /**
     * Invalid user name or password.
     */
    public static final String INVALID_USER_NAME_OR_PASSWORD = "a50002";
    /**
     * Refresh token is required.
     */
    public static final String REFRESH_TOKEN_IS_REQUIRED = "a40020";
    public static final String CHILD_DATA_EXISTS = "cde1000";
    public static final String PARENT_DATA_EXISTS = "cde1001";
    public static final String PARENT_HAS_CHILD = "cde1002";


    public static final String FAILED_TO_JASPER_TEMPLATE = "jr10001";

    /**
     * category exists with this name
     */
    /**
     * Start time must be before end time
     */
    public static final String FAILED_TO_SEND_EMAIL = "e4040";
    public static final String CONFIRM_PASS_REQUIRED = "cp100";
    public static final String USER_EMAIL_EXISTS = "us_100";

    public static final String CHILD_DATA_NOT_FOUND = "CDNF4000";


    /**
     * Email Already exist.
     */
    public static final String EMAIL_ALREADY_EXISTS = "eae229";

    public static final String NOT_FOUND_DYNAMIC = "common04";
    public static final String ID_IS_REQUIRED_DYNAMIC = "common05";
    public static final String DATA_NOT_SAVED_DYNAMIC = "common06";

    public static final String AGENCY_NAME_EXISTS = "ag100";
    public static final String AGENCY_CODE_EXISTS = "ag1001";
    public static final String FARE_INFO_CODE_NAME_EXISTS = "fai100";
}
