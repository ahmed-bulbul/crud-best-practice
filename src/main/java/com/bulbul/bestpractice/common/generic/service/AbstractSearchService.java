package com.bulbul.bestpractice.common.generic.service;


import com.bulbul.bestpractice.common.constant.ApplicationConstant;
import com.bulbul.bestpractice.common.generic.entity.AbstractDomainBasedEntity;
import com.bulbul.bestpractice.common.generic.payload.marker.IDto;
import com.bulbul.bestpractice.common.generic.payload.marker.SDto;
import com.bulbul.bestpractice.common.generic.payload.response.PageData;
import com.bulbul.bestpractice.common.generic.repository.AbstractRepository;
import com.bulbul.bestpractice.common.generic.specification.CustomSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public abstract class AbstractSearchService<E extends AbstractDomainBasedEntity, D extends IDto, S extends SDto>
        extends AbstractService<E, D> implements ISearchService<E, D, S> {
    private final AbstractRepository<E> repository;

    public AbstractSearchService(AbstractRepository<E> repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * search entity by criteria
     *
     * @param searchDto {@link S}
     * @param pageable  {@link Pageable}
     */
    @Override
    public PageData search(S searchDto, Pageable pageable) {
        Specification<E> propellerSpecification = buildSpecification(searchDto).and(new CustomSpecification<E>()
                .active(Objects.nonNull(searchDto.getIsActive()) ? searchDto.getIsActive() : ApplicationConstant.STATUS_TRUE, ApplicationConstant.ACTIVE_FIELD));
        Page<E> pagedData = repository.findAll(propellerSpecification, pageable);
        List<Object> models = pagedData.getContent()
                .stream().map(this::convertToResponseDto).collect(Collectors.toList());
        return PageData.builder()
                .model(models)
                .totalPages(pagedData.getTotalPages())
                .totalElements(pagedData.getTotalElements())
                .currentPage(pageable.getPageNumber() + 1)
                .build();
    }

    protected abstract Specification<E> buildSpecification(S searchDto);
}
