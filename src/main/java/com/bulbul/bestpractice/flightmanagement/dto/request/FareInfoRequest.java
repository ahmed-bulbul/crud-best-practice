package com.bulbul.bestpractice.flightmanagement.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.bulbul.bestpractice.common.generic.payload.marker.IDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FareInfoRequest implements IDto {
    @NotBlank
    private String name;
    @NotNull
    private Long departureAirportId;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime departureTime;
    @NotNull
    private String cost;
    @NotNull
    private String uniqueCode;
}
