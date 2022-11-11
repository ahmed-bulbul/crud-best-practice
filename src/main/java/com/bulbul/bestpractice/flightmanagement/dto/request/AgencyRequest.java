package com.bulbul.bestpractice.flightmanagement.dto.request;

import com.bulbul.bestpractice.common.generic.payload.marker.IDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AgencyRequest implements IDto {
    @NotBlank
    private String name;
    @NotBlank
    private String code;
    private String description;
    @NotEmpty
    private Set<Long> aircraftIds;
}
