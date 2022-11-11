package com.bulbul.bestpractice.flightmanagement.controller;

import com.bulbul.bestpractice.flightmanagement.entity.FareInfo;
import com.bulbul.bestpractice.common.generic.controller.AbstractSearchController;
import com.bulbul.bestpractice.common.generic.payload.seatch.IdQuerySearchDto;
import com.bulbul.bestpractice.flightmanagement.dto.request.FareInfoRequest;
import com.bulbul.bestpractice.flightmanagement.service.FareInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fare-info")
public class FareInfoController extends AbstractSearchController<FareInfo, FareInfoRequest, IdQuerySearchDto> {
    public FareInfoController(FareInfoService iSearchService) {
        super(iSearchService);
    }
}
