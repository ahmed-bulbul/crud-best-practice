package com.bulbul.bestpractice.common.advice;


import com.bulbul.bestpractice.common.exception.ApiError;
import com.bulbul.bestpractice.common.exception.ApplicationError;
import com.bulbul.bestpractice.common.exception.ApplicationServerException;
import com.bulbul.bestpractice.common.exception.ErrorCodeReader;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.bulbul.bestpractice.common.constant.ApplicationConstant;
import com.bulbul.bestpractice.common.constant.ErrorId;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.*;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler implements AccessDeniedHandler {


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError();
        ApplicationError applicationError =
                new ApplicationError(ErrorId.SYSTEM_ERROR, ex.getLocalizedMessage());
        apiError.addError(applicationError);
        ex.printStackTrace();
        return new ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ApiError apiError = new ApiError();
        ApplicationError applicationError =
                new ApplicationError(ErrorId.SYSTEM_ERROR, ex.getLocalizedMessage());
        apiError.addError(applicationError);
        ex.printStackTrace();
        return new ResponseEntity(apiError, HttpStatus.FORBIDDEN);
    }



    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object>
    handleConstraintViolationExceptionAllException(ConstraintViolationException ex, WebRequest request) {
        ApiError apiError = new ApiError();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        violations.forEach(violation -> {
            ApplicationError reservationError = getStoreManagementError(violation.getMessageTemplate());
            apiError.addError(reservationError);
        });
        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApplicationServerException.class)
    public final ResponseEntity<Object> handleEngineeringManagementServerException(
            ApplicationServerException ex, WebRequest request) {
        ApiError apiError = new ApiError();
        ApplicationError reservationError = getStoreManagementError(ex.getErrorId());
        apiError.addError(reservationError);
        return new ResponseEntity(apiError, ex.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            ApplicationError reservationError = getStoreManagementError(error.getDefaultMessage(),
                    buildErrorMessage(error));
            apiError.addError(reservationError);
        }
        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError();
        if (e.getMostSpecificCause() instanceof ApplicationServerException reservationServerException) {
            ApplicationError error = getStoreManagementError(reservationServerException.getErrorId());
            apiError.addError(error);
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        } else if (e.getMostSpecificCause() instanceof InvalidFormatException iex) {
            iex.getPath().forEach(reference -> {
                ApplicationError engineeringManagementError = new ApplicationError(ErrorId.
                        INVALID_DATA_FORMAT_EXCEPTION, iex.getOriginalMessage());
                apiError.addError(engineeringManagementError);
            });
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
        return handleAllExceptions(e, request);
    }

    private ApplicationError getStoreManagementError(String code) {
        ApplicationError engineeringManagementError = ErrorCodeReader.getStoreManagementError(code);
        if (Objects.isNull(engineeringManagementError)) {
            return ErrorCodeReader.getErrorByMessage(code);
        }
        return engineeringManagementError;
    }

    private ApplicationError getStoreManagementError(String code, String message) {
        ApplicationError engineeringManagementError = ErrorCodeReader.getStoreManagementError(code);
        if (Objects.isNull(engineeringManagementError)) {
            return ErrorCodeReader.getErrorByMessage(message);
        }
        return engineeringManagementError;
    }

    private String buildErrorMessage(FieldError error) {
        return capitalize(StringUtils.join(splitByCharacterTypeCamelCase(emptyFieldErrorIfNull(error)
        ), SPACE)) + SPACE + error.getDefaultMessage();
    }

    private String emptyFieldErrorIfNull(FieldError fieldError) {
        return Objects.isNull(fieldError) ? ApplicationConstant.EMPTY_STRING : fieldError.getField();
    }


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.sendError(403, "Your Message");
        response.setStatus(403);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Your Message");
    }
}
