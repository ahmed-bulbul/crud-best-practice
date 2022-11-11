package com.bulbul.bestpractice.common.generic.service;


import com.bulbul.bestpractice.common.constant.ApplicationConstant;
import com.bulbul.bestpractice.common.constant.ErrorId;
import com.bulbul.bestpractice.common.exception.ApplicationServerException;
import com.bulbul.bestpractice.common.generic.entity.AbstractDomainBasedEntity;
import com.bulbul.bestpractice.common.generic.payload.marker.IDto;
import com.bulbul.bestpractice.common.generic.repository.AbstractRepository;
import com.bulbul.bestpractice.common.utils.Helper;
import com.bulbul.bestpractice.common.generic.payload.response.PageData;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractService<E extends AbstractDomainBasedEntity, D extends IDto>
        implements IService<E, D> {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);
    private final AbstractRepository<E> repository;

    @Override
    public <T> T getSingle(Long id) {
        return convertToResponseDto(findByIdUnfiltered(id));
    }

    @Override
    public E findById(Long id) {
        return findOptionalById(id, ApplicationConstant.STATUS_TRUE)
                .orElseThrow(() -> buildException(ErrorId.NOT_FOUND_DYNAMIC, ErrorId.DATA_NOT_FOUND));
    }

    @Override
    public E findByIdUnfiltered(Long id) {
        return findOptionalById(id, ApplicationConstant.STATUS_FALSE)
                .orElseThrow(() -> buildException(ErrorId.NOT_FOUND_DYNAMIC, ErrorId.DATA_NOT_FOUND));
    }

    @Override
    public Optional<E> findOptionalById(Long id, Boolean activeRequired) {
        if (ObjectUtils.isEmpty(id)) {
            throw buildException(ErrorId.ID_IS_REQUIRED_DYNAMIC, ErrorId.ID_IS_REQUIRED);
        }
        if (activeRequired == Boolean.TRUE) {
            return repository.findByIdAndIsActive(id, ApplicationConstant.STATUS_TRUE);
        }
        return repository.findById(id);
    }

    @Override
    public PageData getAll(Boolean isActive, Pageable pageable) {
        Page<E> pagedData = repository.findAllByIsActive(isActive, pageable);
        List<Object> models = pagedData.getContent().stream().map(this::convertToResponseDto)
                .collect(Collectors.toList());
        return PageData.builder()
                .model(models)
                .totalPages(pagedData.getTotalPages())
                .totalElements(pagedData.getTotalElements())
                .currentPage(pageable.getPageNumber() + 1)
                .build();
    }

    @Override
    public Collection<E> getAllByDomainIdIn(Set<Long> ids, Boolean isActive) {
        return repository.findAllByIdInAndIsActive(ids, isActive);
    }

    @Override
    public List<E> getAllByDomainIdInUnfiltered(Set<Long> ids) {
        return repository.findAllByIdIn(ids);
    }

    @Override
    public void updateActiveStatus(Long id, Boolean isActive) {
        E e = findByIdUnfiltered(id);
        if (Objects.equals(e.getIsActive(), isActive)) {
            throw ApplicationServerException.badRequest(ErrorId.ONLY_TOGGLE_VALUE_ACCEPTED);
        }

        e.setIsActive(isActive);
        saveItem(e);
    }

    @Override
    public E create(D d) {
        validateClientData(d, null);
        E entity = convertToEntity(d);
        return saveItem(entity);
    }

    @Override
    public E update(D d, Long id) {
        validateClientData(d, id);
        final E entity = updateEntity(d, findByIdUnfiltered(id));
        return saveItem(entity);
    }

    @Override
    public E saveItem(E entity) {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            String name = entity.getClass().getSimpleName();
            LOGGER.error("Save failed for entity {}", name);
            LOGGER.error("Error message: {}", e.getMessage());
            throw ApplicationServerException.dataSaveException(Helper.createDynamicCode(ErrorId.DATA_NOT_SAVED_DYNAMIC,
                    name));
        }
    }

    /**
     * This method is responsible for batch save
     *
     * @param entityList {@link List<E>}
     * @return List<E>
     */
    @Override
    public List<E> saveItemList(List<E> entityList) {
        try {
            if (CollectionUtils.isEmpty(entityList)) {
                return entityList;
            }
            return repository.saveAll(entityList);
        } catch (Exception e) {
            String entityName = entityList.get(0).getClass().getSimpleName();
            LOGGER.error("Save failed for entity {}", entityName);
            LOGGER.error("Error message: {}", e.getMessage());
            throw ApplicationServerException.dataSaveException(Helper.createDynamicCode(ErrorId.DATA_NOT_SAVED_DYNAMIC,
                    entityName));
        }
    }


    protected abstract <T> T convertToResponseDto(E e);

    protected abstract E convertToEntity(D d);

    protected abstract E updateEntity(D dto, E entity);

    private ApplicationServerException buildException(String dynamicMessage, String staticMessage) {
        try {
            String typeName = ((Class) (((ParameterizedType) this.getClass().getGenericSuperclass())
                    .getActualTypeArguments()[ApplicationConstant.FIRST_INDEX])).getSimpleName();
            return ApplicationServerException.notFound(Helper.createDynamicCode(dynamicMessage,
                    typeName));
        } catch (Exception e) {
            LOGGER.error("--- Could not determine entity name ---");
            return ApplicationServerException.notFound(staticMessage);
        }
    }
}
