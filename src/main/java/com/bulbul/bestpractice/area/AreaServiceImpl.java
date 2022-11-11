package com.bulbul.bestpractice.area;


import com.bulbul.bestpractice.common.generic.repository.AbstractRepository;
import com.bulbul.bestpractice.common.generic.service.AbstractSearchService;
import com.bulbul.bestpractice.common.generic.specification.CustomSpecification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


/**
 * AreaService Impl
 * @author Ashraful
 */
@Service
public class AreaServiceImpl extends AbstractSearchService<Area, AreaDto, AreaSearchDto> {
    private static final String STATUS_ID = "statusId";
    private static final String NAME = "name";

    /**
     * Autowired Constructor
     * @param repository {@link AbstractRepository}
     */
    public AreaServiceImpl(AbstractRepository<Area> repository) {
        super(repository);
    }


    @Override
    protected Specification<Area> buildSpecification(AreaSearchDto searchDto) {
        CustomSpecification<Area> customSpecification = new CustomSpecification<>();
        return Specification.where(customSpecification.likeSpecificationAtRoot(searchDto.getName(),NAME )
                .and(customSpecification.equalSpecificationAtRoot(searchDto.getStatusId(),STATUS_ID))
        );
    }

    @Override
    protected AreaViewModel convertToResponseDto(Area area) {
        return AreaViewModel.builder()
                .id(area.getId())
                .code(area.getCode())
                .name(area.getName())
                .description(area.getDescription())
                .build();
    }

    @Override
    protected Area convertToEntity(AreaDto areaDto) {
        return Area.builder()
                .name(areaDto.getName())
                .code(areaDto.getCode())
                .description(areaDto.getDescription())
                .build();
    }

    @Override
    protected Area updateEntity(AreaDto areaDto, Area area) {
        area.setName(areaDto.getName());
        area.setCode(areaDto.getCode());
        area.setDescription(areaDto.getDescription());
        return area;
    }



}
