package com.bulbul.bestpractice.common.generic.controller;


import com.bulbul.bestpractice.common.generic.entity.AbstractEntity;
import com.bulbul.bestpractice.common.generic.payload.marker.IDto;
import com.bulbul.bestpractice.common.generic.payload.marker.SDto;
import com.bulbul.bestpractice.common.generic.payload.response.PageData;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ISearchController<E extends AbstractEntity, D extends IDto, S extends SDto> extends IController<E, D> {
    ResponseEntity<PageData> search(S s, Pageable pageable);
}
