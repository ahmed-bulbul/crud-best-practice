package com.bulbul.bestpractice.flightmanagement.dto.search;

import com.bulbul.bestpractice.common.constant.ApplicationConstant;
import com.bulbul.bestpractice.common.generic.payload.marker.SDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AgencyCustomSearch implements SDto {
    private String name;
    private String code;
    private Boolean IsActive = ApplicationConstant.STATUS_TRUE;

}
