package com.bulbul.bestpractice.common.generic.payload.seatch;


import com.bulbul.bestpractice.common.constant.ApplicationConstant;
import com.bulbul.bestpractice.common.generic.payload.marker.SDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class IdQuerySearchDto implements SDto {
    private String query;
    private Long id;
    private Boolean isActive = ApplicationConstant.STATUS_TRUE;
}