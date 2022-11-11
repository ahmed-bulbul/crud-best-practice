package com.bulbul.bestpractice.flightmanagement.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FareInfoResponse {
    private Long id;
    private String name;
    private Long departureAirportId;
    private String airportName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime departureTime;
    private String cost;
    private String uniqueCode;
}
