package com.bulbul.bestpractice.flightmanagement.service;

import com.bulbul.bestpractice.common.constant.ApplicationConstant;
import com.bulbul.bestpractice.common.constant.ErrorId;
import com.bulbul.bestpractice.common.exception.ApplicationServerException;
import com.bulbul.bestpractice.common.generic.payload.response.PageData;
import com.bulbul.bestpractice.common.generic.service.AbstractSearchService;
import com.bulbul.bestpractice.common.generic.specification.CustomSpecification;
import com.bulbul.bestpractice.flightmanagement.dto.projection.AgencyProjection;
import com.bulbul.bestpractice.flightmanagement.dto.request.AgencyRequest;
import com.bulbul.bestpractice.flightmanagement.dto.response.AgencyResponse;
import com.bulbul.bestpractice.flightmanagement.dto.response.AircraftResponse;
import com.bulbul.bestpractice.flightmanagement.dto.search.AgencyCustomSearch;
import com.bulbul.bestpractice.flightmanagement.entity.Agency;
import com.bulbul.bestpractice.flightmanagement.entity.Aircraft;
import com.bulbul.bestpractice.flightmanagement.repository.AgencyRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AgencyService extends AbstractSearchService<Agency, AgencyRequest, AgencyCustomSearch> {
    private final AgencyRepository agencyRepository;
    private final AircraftService aircraftService;

    public AgencyService(AgencyRepository agencyRepository, AircraftService aircraftService) {
        super(agencyRepository);
        this.agencyRepository = agencyRepository;
        this.aircraftService = aircraftService;
    }

    @Override
    public PageData getAll(Boolean isActive, Pageable pageable) {
        Page<Agency> pagedData = agencyRepository.findAllByIsActive(isActive, pageable);
        List<AgencyResponse> models = getAgencyResponses(pagedData);
        return PageData.builder()
                .model(models)
                .totalPages(pagedData.getTotalPages())
                .totalElements(pagedData.getTotalElements())
                .currentPage(pageable.getPageNumber() + 1)
                .build();
    }

    @Override
    public PageData search(AgencyCustomSearch searchDto, Pageable pageable) {
        Page<Agency> pagedData = agencyRepository.findAll(this.buildSpecification(searchDto), pageable);
        List<AgencyResponse> models = getAgencyResponses(pagedData);
        return PageData.builder()
                .model(models)
                .totalPages(pagedData.getTotalPages())
                .totalElements(pagedData.getTotalElements())
                .currentPage(pageable.getPageNumber() + 1)
                .build();
    }

    @Override
    protected Specification<Agency> buildSpecification(AgencyCustomSearch searchDto) {
        CustomSpecification<Agency> customSpecification = new CustomSpecification<>();
        return Specification.where(customSpecification.active(searchDto.getIsActive(), ApplicationConstant.ACTIVE_FIELD))
                .and(customSpecification.likeSpecificationAtRoot(searchDto.getName(), ApplicationConstant.ENTITY_NAME))
                .and(customSpecification.likeSpecificationAtRoot(searchDto.getCode(), ApplicationConstant.ENTITY_CODE));
    }

    private List<AgencyResponse> getAgencyResponses(Page<Agency> pagedData) {
        List<Agency> agencies = pagedData.getContent();
        Map<Long, Set<AircraftResponse>> aircraftMap = agencies.stream().map(Agency::getAircraftSet).
                flatMap(Collection::stream).collect(Collectors.groupingBy(Aircraft::getAgencyId, Collectors.mapping(
                        aircraft -> AircraftResponse.builder().code(aircraft.getCode()).id(aircraft.getId()).build()
                        , Collectors.toSet()
                )));
        return agencies.stream().map(agency -> AgencyResponse.builder()
                .id(agency.getId())
                .aircraft(aircraftMap.get(agency.getId()))
                .name(agency.getName())
                .description(agency.getDescription())
                .code(agency.getCode())
                .build()).collect(Collectors.toList());

    }

    @Override
    protected AgencyResponse convertToResponseDto(Agency agency) {
        Set<AircraftResponse> aircraftResponses = aircraftService.convertToResponseList(agency.getAircraftSet());
        return AgencyResponse.builder()
                .id(agency.getId())
                .description(agency.getDescription())
                .name(agency.getName())
                .aircraft(CollectionUtils.isNotEmpty(aircraftResponses) ? aircraftResponses : Collections.emptySet())
                .build();
    }

    @Override
    protected Agency convertToEntity(AgencyRequest agencyRequest) {
        validate(agencyRequest, null);
        Set<Aircraft> aircrafts = new HashSet<>(aircraftService.getAllByDomainIdIn(agencyRequest.getAircraftIds(),
                ApplicationConstant.STATUS_TRUE));
        return Agency.builder()
                .aircraftSet(aircrafts)
                .description(agencyRequest.getDescription())
                .code(agencyRequest.getCode())
                .name(agencyRequest.getName())
                .build();
    }

    @Override
    protected Agency updateEntity(AgencyRequest dto, Agency entity) {
        validate(dto, entity);
        entity.setDescription(dto.getDescription());
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        return entity;
    }

    public Set<AgencyProjection> getAgencyByIds(Set<Long> agencyIds, Boolean status) {
        return agencyRepository.findByIdInAndIsActive(agencyIds, status);
    }

    private void validate(AgencyRequest dto, Agency old) {
        Set<Agency> agencySet = agencyRepository.findAllByNameIgnoreCaseOrCodeIgnoreCase(dto.getName(), dto.getCode());
        if (!CollectionUtils.isEmpty(agencySet)) {
            agencySet.forEach(agency -> {
                if (Objects.nonNull(old) && agency.equals(old)) {
                    return;
                }
                if (agency.getCode().equalsIgnoreCase(dto.getCode())) {
                    throw ApplicationServerException.badRequest(
                            ErrorId.AGENCY_CODE_EXISTS);
                }
                if (agency.getName().equals(dto.getName())) {
                    throw ApplicationServerException.badRequest(
                            ErrorId.AGENCY_NAME_EXISTS);
                }
            });
        }
    }

}
