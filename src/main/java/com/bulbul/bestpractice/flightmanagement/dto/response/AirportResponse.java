package com.bulbul.bestpractice.flightmanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AirportResponse {
    private Long id;
    private String name;
    private String code;
}
