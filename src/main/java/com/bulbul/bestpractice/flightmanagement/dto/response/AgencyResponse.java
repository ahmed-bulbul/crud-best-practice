package com.bulbul.bestpractice.flightmanagement.dto.response;

import com.bulbul.bestpractice.common.generic.payload.marker.IDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AgencyResponse implements IDto {
    private Long id;
    private String name;
    private String code;
    private String description;
    private Set<AircraftResponse> aircraft;
}
