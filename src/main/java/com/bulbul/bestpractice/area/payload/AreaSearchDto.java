package com.bulbul.bestpractice.area.payload;


import com.bulbul.bestpractice.common.generic.payload.marker.SDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AreaSearchDto
 * @author Ashraful
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AreaSearchDto implements SDto {
    private Integer statusId;
    private String name;
}
