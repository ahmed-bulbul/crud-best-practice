package com.bulbul.bestpractice.category.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryResponseDto {

    private Long id;
    private String name;
    private String description;
    private String code;
}
