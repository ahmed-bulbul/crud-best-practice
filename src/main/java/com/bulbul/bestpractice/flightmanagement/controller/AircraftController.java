package com.bulbul.bestpractice.flightmanagement.controller;

import com.bulbul.bestpractice.flightmanagement.entity.Aircraft;
import com.bulbul.bestpractice.common.generic.controller.AbstractSearchController;
import com.bulbul.bestpractice.common.generic.payload.seatch.IdQuerySearchDto;
import com.bulbul.bestpractice.flightmanagement.dto.request.AircraftRequest;
import com.bulbul.bestpractice.flightmanagement.service.AircraftService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController extends AbstractSearchController<Aircraft, AircraftRequest, IdQuerySearchDto> {
    public AircraftController(AircraftService iSearchService) {
        super(iSearchService);
    }
}
