package com.bulbul.bestpractice.area.controller;


import com.bulbul.bestpractice.area.entity.Area;
import com.bulbul.bestpractice.area.payload.AreaDto;
import com.bulbul.bestpractice.area.payload.AreaSearchDto;
import com.bulbul.bestpractice.area.service.AreaService;
import com.bulbul.bestpractice.common.generic.controller.AbstractSearchController;
import com.bulbul.bestpractice.common.generic.service.ISearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AreaController
 * @author Bulbul Ahmed
 */
@RestController
@RequestMapping("/api/area")
public class AreaController extends AbstractSearchController<Area, AreaDto, AreaSearchDto> {
    /**
     * Autowired Constructor
     *
     * @param iSearchService {@link ISearchService}
     */


    private final AreaService areaService;

    public AreaController(ISearchService<Area, AreaDto, AreaSearchDto> iSearchService, AreaService areaService) {
        super(iSearchService);
        this.areaService = areaService;
    }

    @GetMapping("/testFunc")
    public String testFunction(){
        return areaService.testFunction();
    }
}
