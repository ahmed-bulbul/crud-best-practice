package com.bulbul.bestpractice.area.payload;

import lombok.*;

/**
 * AreaViewModel
 * @author Ashraful
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AreaViewModel {
    private Long id;
    private String name;
    private String code;
    private String description;
}
