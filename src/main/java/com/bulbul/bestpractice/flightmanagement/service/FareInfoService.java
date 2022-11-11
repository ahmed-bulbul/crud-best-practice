package com.bulbul.bestpractice.flightmanagement.service;

import com.bulbul.bestpractice.common.utils.Helper;
import com.bulbul.bestpractice.flightmanagement.entity.Airport;
import com.bulbul.bestpractice.flightmanagement.entity.FareInfo;
import com.bulbul.bestpractice.flightmanagement.repository.FareInfoRepository;
import com.bulbul.bestpractice.user.service.AssignUserService;
import com.bulbul.bestpractice.common.constant.ApplicationConstant;
import com.bulbul.bestpractice.common.constant.ErrorId;
import com.bulbul.bestpractice.common.exception.ApplicationServerException;
import com.bulbul.bestpractice.common.generic.payload.response.PageData;
import com.bulbul.bestpractice.common.generic.payload.seatch.IdQuerySearchDto;
import com.bulbul.bestpractice.common.generic.service.AbstractSearchService;
import com.bulbul.bestpractice.common.generic.specification.CustomSpecification;
import com.bulbul.bestpractice.flightmanagement.dto.projection.FareInfoProjection;
import com.bulbul.bestpractice.flightmanagement.dto.request.FareInfoRequest;
import com.bulbul.bestpractice.flightmanagement.dto.response.FareInfoResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FareInfoService extends AbstractSearchService<FareInfo, FareInfoRequest, IdQuerySearchDto> {
    private final FareInfoRepository fareInfoRepository;
    private final AirportService airportService;
    private final AssignUserService assignUserService;

    public FareInfoService(FareInfoRepository fareInfoRepository, AirportService airportService,
                           @Lazy AssignUserService assignUserService) {
        super(fareInfoRepository);
        this.fareInfoRepository = fareInfoRepository;
        this.airportService = airportService;
        this.assignUserService = assignUserService;
    }

    @Override
    public PageData getAll(Boolean isActive, Pageable pageable) {
        Page<FareInfo> pagedData = fareInfoRepository.findAllByIsActive(isActive, pageable);
        List<FareInfoResponse> models = getFareInfoResponses(pagedData);

        return PageData.builder()
                .model(models)
                .totalPages(pagedData.getTotalPages())
                .totalElements(pagedData.getTotalElements())
                .currentPage(pageable.getPageNumber() + 1)
                .build();
    }

    @Override
    public PageData search(IdQuerySearchDto searchDto, Pageable pageable) {
        Page<FareInfo> pagedData = fareInfoRepository.findAll(this.buildSpecification(searchDto), pageable);
        List<FareInfoResponse> models = getFareInfoResponses(pagedData);

        return PageData.builder()
                .model(models)
                .totalPages(pagedData.getTotalPages())
                .totalElements(pagedData.getTotalElements())
                .currentPage(pageable.getPageNumber() + 1)
                .build();

    }

    private List<FareInfoResponse> getFareInfoResponses(Page<FareInfo> pagedData) {
        List<FareInfo> fareInfoList = pagedData.getContent();
        Set<Long> departAirportIds = fareInfoList.stream().map(FareInfo::getDepartureAirportId).collect(Collectors.toSet());
        Map<Long, Airport> airportMap = airportService.getAllByDomainIdIn(departAirportIds, ApplicationConstant.STATUS_TRUE)
                .stream().collect(Collectors.toMap(Airport::getId, Function.identity()));

        return fareInfoList.stream().map(fareInfo -> getResponse(fareInfo,
                airportMap.get(fareInfo.getDepartureAirportId()))).collect(Collectors.toList());
    }

    private FareInfoResponse getResponse(FareInfo fareInfo, Airport airport) {
        return FareInfoResponse.builder()
                .id(fareInfo.getId())
                .uniqueCode(fareInfo.getUniqueCode())
                .name(fareInfo.getName())
                .airportName(airport.getName())
                .cost(fareInfo.getCost())
                .departureTime(fareInfo.getDepartureTime())
                .departureAirportId(fareInfo.getDepartureAirportId())
                .build();
    }

    @Override
    protected Specification<FareInfo> buildSpecification(IdQuerySearchDto searchDto) {
        CustomSpecification<FareInfo> customSpecification = new CustomSpecification<>();
        return Specification.where(customSpecification.active(searchDto.getIsActive(), ApplicationConstant.ACTIVE_FIELD))
                .and(customSpecification.likeSpecificationAtRoot(searchDto.getQuery(), ApplicationConstant.FARE_INFO_UNIQUE_CODE));
    }

    @Override
    public void updateActiveStatus(Long id, Boolean isActive) {
        FareInfo fareInfo = findByIdUnfiltered(id);
        if (Objects.equals(fareInfo.getIsActive(), isActive)) {
            throw ApplicationServerException.badRequest(ErrorId.ONLY_TOGGLE_VALUE_ACCEPTED);
        }
        if (isActive == Boolean.FALSE && assignUserService.existByFareInfoId(id, ApplicationConstant.STATUS_TRUE)) {
            throw ApplicationServerException.badRequest(Helper.createDynamicCode(ErrorId.PARENT_HAS_CHILD,
                    ApplicationConstant.ASSIGN_USER));
        }
        fareInfo.setIsActive(isActive);
        saveItem(fareInfo);
    }

    @Override
    protected FareInfoResponse convertToResponseDto(FareInfo fareInfo) {
        return FareInfoResponse.builder()
                .id(fareInfo.getId())
                .uniqueCode(fareInfo.getUniqueCode())
                .name(fareInfo.getName())
                .airportName(fareInfo.getDepartureAirport().getName())
                .cost(fareInfo.getCost())
                .departureTime(fareInfo.getDepartureTime())
                .departureAirportId(fareInfo.getDepartureAirport().getId())
                .build();
    }

    @Override
    protected FareInfo convertToEntity(FareInfoRequest fareInfoRequest) {
        validate(fareInfoRequest, null);
        Airport departAirport = airportService.findById(fareInfoRequest.getDepartureAirportId());
        return FareInfo.builder()
                .cost(fareInfoRequest.getCost())
                .departureAirport(departAirport)
                .departureTime(fareInfoRequest.getDepartureTime())
                .name(fareInfoRequest.getName())
                .uniqueCode(fareInfoRequest.getUniqueCode())
                .build();
    }

    @Override
    protected FareInfo updateEntity(FareInfoRequest dto, FareInfo entity) {
        validate(dto, entity);
        Airport departAirport = airportService.findById(dto.getDepartureAirportId());
        if (!Objects.equals(departAirport, entity.getDepartureAirport())) {
            entity.setDepartureAirport(departAirport);
        }
        entity.setName(dto.getName());
        entity.setCost(dto.getCost());
        entity.setUniqueCode(dto.getUniqueCode());
        entity.setDepartureTime(dto.getDepartureTime());
        return entity;
    }

    public Set<FareInfoProjection> getAllFareByIds(Set<Long> fareIds, Boolean status) {
        return fareInfoRepository.findByIdInAndIsActive(fareIds, status);
    }

    private void validate(FareInfoRequest dto, FareInfo old) {
        Set<FareInfo> fareInfoSet = fareInfoRepository.findAllByUniqueCodeIgnoreCase(dto.getUniqueCode());
        if (!CollectionUtils.isEmpty(fareInfoSet)) {
            fareInfoSet.forEach(fareInfo -> {
                if (Objects.nonNull(old) && fareInfo.equals(old)) {
                    return;
                }
                if (fareInfo.getUniqueCode().equals(dto.getUniqueCode())) {
                    throw ApplicationServerException.badRequest(
                            ErrorId.FARE_INFO_CODE_NAME_EXISTS);
                }
            });
        }
    }
}
