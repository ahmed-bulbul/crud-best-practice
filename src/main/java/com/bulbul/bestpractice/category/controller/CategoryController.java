package com.bulbul.bestpractice.category.controller;


import com.bulbul.bestpractice.category.dto.request.CategoryRequestDto;
import com.bulbul.bestpractice.category.entity.Category;
import com.bulbul.bestpractice.category.service.CategoryService;
import com.bulbul.bestpractice.common.generic.controller.AbstractSearchController;
import com.bulbul.bestpractice.common.generic.payload.seatch.IdQuerySearchDto;
import com.bulbul.bestpractice.common.generic.service.ISearchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController extends AbstractSearchController<Category, CategoryRequestDto, IdQuerySearchDto> {

    public CategoryController(CategoryService categoryService) {
        super(categoryService);
    }
}
