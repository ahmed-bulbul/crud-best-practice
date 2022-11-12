package com.bulbul.bestpractice.category.dto.request;


import com.bulbul.bestpractice.common.generic.payload.marker.IDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryRequestDto implements IDto {
    private String name;
    private String description;
    private String code;
}
