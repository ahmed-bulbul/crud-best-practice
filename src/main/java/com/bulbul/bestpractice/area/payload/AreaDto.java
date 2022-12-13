package com.bulbul.bestpractice.area.payload;


import com.bulbul.bestpractice.common.generic.payload.marker.IDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * AreaDto
 * @author Ashraful
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AreaDto implements IDto {
    @NotNull
    private String name;
    @NotNull
    private String code;
    private String description;
}
