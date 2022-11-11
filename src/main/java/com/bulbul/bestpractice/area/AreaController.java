package com.bulbul.bestpractice.area;


import com.bulbul.bestpractice.common.generic.controller.AbstractSearchController;
import com.bulbul.bestpractice.common.generic.service.ISearchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AreaController
 * @author Ashraful
 */
@RestController
@RequestMapping("/api/area")
public class AreaController extends AbstractSearchController<Area, AreaDto, AreaSearchDto> {
    /**
     * Autowired Constructor
     *
     * @param iSearchService {@link ISearchService}
     */
    public AreaController(ISearchService<Area, AreaDto, AreaSearchDto> iSearchService) {
        super(iSearchService);
    }
}
