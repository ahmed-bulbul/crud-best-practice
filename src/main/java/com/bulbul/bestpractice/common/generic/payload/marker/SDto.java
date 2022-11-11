package com.bulbul.bestpractice.common.generic.payload.marker;


import com.bulbul.bestpractice.common.constant.ApplicationConstant;

/**
 * Marker interface for search dto.
 *
 * @author Masud Rana
 */
public interface SDto {
    default Boolean getIsActive() {
        return ApplicationConstant.STATUS_TRUE;
    }
}
