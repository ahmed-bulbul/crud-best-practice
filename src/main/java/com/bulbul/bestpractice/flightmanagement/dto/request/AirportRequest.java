package com.bulbul.bestpractice.flightmanagement.dto.request;

import com.bulbul.bestpractice.common.generic.payload.marker.IDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AirportRequest implements IDto {
    @NotBlank
    private String name;
    @NotBlank
    private String code;
}
