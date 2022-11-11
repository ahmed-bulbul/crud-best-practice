package com.bulbul.bestpractice.flightmanagement.controller;

import com.bulbul.bestpractice.flightmanagement.entity.Agency;
import com.bulbul.bestpractice.flightmanagement.service.AgencyService;
import com.bulbul.bestpractice.common.generic.controller.AbstractSearchController;
import com.bulbul.bestpractice.flightmanagement.dto.request.AgencyRequest;
import com.bulbul.bestpractice.flightmanagement.dto.search.AgencyCustomSearch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agency")
public class AgencyController extends AbstractSearchController<Agency, AgencyRequest, AgencyCustomSearch> {
    public AgencyController(AgencyService iSearchService) {
        super(iSearchService);
    }
}
