package com.bulbul.bestpractice.flightmanagement.service;

import com.bulbul.bestpractice.flightmanagement.repository.AirportRepository;
import com.bulbul.bestpractice.common.generic.payload.seatch.IdQuerySearchDto;
import com.bulbul.bestpractice.common.generic.service.AbstractSearchService;
import com.bulbul.bestpractice.flightmanagement.dto.request.AirportRequest;
import com.bulbul.bestpractice.flightmanagement.dto.response.AirportResponse;
import com.bulbul.bestpractice.flightmanagement.entity.Airport;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AirportService extends AbstractSearchService<Airport, AirportRequest, IdQuerySearchDto> {
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        super(airportRepository);
        this.airportRepository = airportRepository;
    }

    @Override
    protected Specification<Airport> buildSpecification(IdQuerySearchDto searchDto) {
        return null;
    }

    @Override
    protected AirportResponse convertToResponseDto(Airport airport) {
        return AirportResponse.builder()
                .name(airport.getName())
                .code(airport.getCode())
                .id(airport.getId())
                .build();
    }

    @Override
    protected Airport convertToEntity(AirportRequest airportRequest) {
        return Airport.builder()
                .code(airportRequest.getCode())
                .name(airportRequest.getName())
                .build();
    }

    @Override
    protected Airport updateEntity(AirportRequest dto, Airport entity) {
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        return entity;
    }
}
