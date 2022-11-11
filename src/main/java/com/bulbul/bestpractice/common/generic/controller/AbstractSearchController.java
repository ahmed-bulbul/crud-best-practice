package com.bulbul.bestpractice.common.generic.controller;


import com.bulbul.bestpractice.common.constant.ApplicationConstant;
import com.bulbul.bestpractice.common.generic.entity.AbstractDomainBasedEntity;
import com.bulbul.bestpractice.common.generic.payload.marker.IDto;
import com.bulbul.bestpractice.common.generic.payload.marker.SDto;
import com.bulbul.bestpractice.common.generic.payload.response.PageData;
import com.bulbul.bestpractice.common.generic.service.ISearchService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public abstract class AbstractSearchController<E extends AbstractDomainBasedEntity, D extends IDto, S extends SDto>
        extends AbstractController<E, D> implements ISearchController<E, D, S> {
    protected final ISearchService<E, D, S> iSearchService;

    public AbstractSearchController(ISearchService<E, D, S> iSearchService) {
        super(iSearchService);
        this.iSearchService = iSearchService;
    }

    /**
     * search entities by criteria
     *
     * @param searchDto {@link S}
     * @param pageable  {@link Pageable}
     * @return {@link PageData}
     */
    @PostMapping("/search")
    @Override
    public ResponseEntity<PageData> search(@RequestBody @Valid S searchDto,
                                           @PageableDefault(
                                                   sort = ApplicationConstant.DEFAULT_SORT,
                                                   direction = Sort.Direction.ASC) Pageable pageable) {
        return new ResponseEntity<>(iSearchService.search(searchDto, pageable), HttpStatus.OK);
    }

}
