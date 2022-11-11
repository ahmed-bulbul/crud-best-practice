package com.bulbul.bestpractice.flightmanagement.service;

import com.bulbul.bestpractice.flightmanagement.entity.Aircraft;
import com.bulbul.bestpractice.common.generic.payload.seatch.IdQuerySearchDto;
import com.bulbul.bestpractice.common.generic.repository.AbstractRepository;
import com.bulbul.bestpractice.common.generic.service.AbstractSearchService;
import com.bulbul.bestpractice.flightmanagement.dto.request.AircraftRequest;
import com.bulbul.bestpractice.flightmanagement.dto.response.AircraftResponse;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AircraftService extends AbstractSearchService<Aircraft, AircraftRequest, IdQuerySearchDto> {
    public AircraftService(AbstractRepository<Aircraft> repository) {
        super(repository);
    }

    @Override
    protected Specification<Aircraft> buildSpecification(IdQuerySearchDto searchDto) {
        return null;
    }

    @Override
    protected AircraftResponse convertToResponseDto(Aircraft aircraft) {
        return AircraftResponse.builder()
                .id(aircraft.getId())
                .code(aircraft.getCode())
                .build();
    }

    @Override
    protected Aircraft convertToEntity(AircraftRequest aircraftRequest) {
        return Aircraft.builder()
                .code(aircraftRequest.getCode())
                .build();
    }

    @Override
    protected Aircraft updateEntity(AircraftRequest dto, Aircraft entity) {
        entity.setCode(dto.getCode());
        return entity;
    }

    public Set<AircraftResponse> convertToResponseList(Set<Aircraft> aircrafts) {
        return aircrafts.stream().map(this::convertToResponseDto).collect(Collectors.toSet());
    }
}
