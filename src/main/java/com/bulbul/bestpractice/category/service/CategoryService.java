package com.bulbul.bestpractice.category.service;


import com.bulbul.bestpractice.category.dto.request.CategoryRequestDto;
import com.bulbul.bestpractice.category.dto.response.CategoryResponseDto;
import com.bulbul.bestpractice.category.entity.Category;
import com.bulbul.bestpractice.category.repository.CategoryRepository;
import com.bulbul.bestpractice.common.generic.payload.seatch.IdQuerySearchDto;
import com.bulbul.bestpractice.common.generic.service.AbstractSearchService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends AbstractSearchService<Category, CategoryRequestDto, IdQuerySearchDto> {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
    }


    @Override
    protected Specification<Category> buildSpecification(IdQuerySearchDto searchDto) {
        return null;
    }

    @Override
    protected CategoryResponseDto convertToResponseDto(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .code(category.getCode())
                .build();
    }

    @Override
    protected Category convertToEntity(CategoryRequestDto categoryRequestDto) {
        return Category.builder()
                .name(categoryRequestDto.getName())
                .code(categoryRequestDto.getCode())
                .description(categoryRequestDto.getDescription())
                .build();
    }

    @Override
    protected Category updateEntity(CategoryRequestDto dto, Category entity) {
        return null;
    }

    @Override
    public Boolean validateClientData(CategoryRequestDto categoryRequestDto, Long id) {
        return super.validateClientData(categoryRequestDto, id);
    }
}
