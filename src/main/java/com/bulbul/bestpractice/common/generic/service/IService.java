package com.bulbul.bestpractice.common.generic.service;


import com.bulbul.bestpractice.common.generic.entity.AbstractEntity;
import com.bulbul.bestpractice.common.generic.payload.marker.IDto;
import com.bulbul.bestpractice.common.generic.payload.response.PageData;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IService<E extends AbstractEntity, D extends IDto> {

    <T> T getSingle(Long id);

    E findById(Long id);

    E findByIdUnfiltered(Long id);

    Optional<E> findOptionalById(Long id, Boolean isActive);

    PageData getAll(Boolean isActive, Pageable pageable);

    Collection<E> getAllByDomainIdIn(Set<Long> ids, Boolean isActive);

    E create(D d);

    E update(D d, Long id);

    List<E> getAllByDomainIdInUnfiltered(Set<Long> ids);

    void updateActiveStatus(Long id, Boolean isActive);

    E saveItem(E entity);

    List<E> saveItemList(List<E> entityList);

    default Boolean validateClientData(D d, Long id) {
        return Boolean.TRUE;
    }
}
