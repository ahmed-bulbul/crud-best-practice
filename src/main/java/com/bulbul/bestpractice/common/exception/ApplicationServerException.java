package com.bulbul.bestpractice.common.exception;


import com.bulbul.bestpractice.common.constant.ApplicationConstant;
import lombok.Data;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;

@Data
public class ApplicationServerException extends RuntimeException {
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1436995162658277359L;
    /**
     * Error id.
     */
    private final String errorId;

    /**
     * trace id.
     */
    private final String traceId;

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public ApplicationServerException(String errorId, HttpStatus status, String traceId) {
        this.errorId = errorId;
        this.traceId = traceId;
        this.status = status;
    }

    public static ApplicationServerException badRequest(String errorId) {
        return new ApplicationServerException(errorId, HttpStatus.BAD_REQUEST, MDC.get(
                ApplicationConstant.TRACE_ID));
    }

    public static ApplicationServerException notFound(String errorId) {
        return new ApplicationServerException(errorId, HttpStatus.NOT_FOUND, MDC.get(
                ApplicationConstant.TRACE_ID));
    }

    public static ApplicationServerException dataSaveException(String errorId) {
        return new ApplicationServerException(errorId, HttpStatus.INTERNAL_SERVER_ERROR,
                MDC.get(ApplicationConstant.TRACE_ID));
    }

    public static ApplicationServerException internalServerException(String errorId) {
        return new ApplicationServerException(errorId, HttpStatus.INTERNAL_SERVER_ERROR,
                MDC.get(ApplicationConstant.TRACE_ID));
    }

    public static ApplicationServerException invalidUserException(String errorId) {
        return new ApplicationServerException(errorId, HttpStatus.UNAUTHORIZED,
                MDC.get(ApplicationConstant.TRACE_ID));
    }
}
