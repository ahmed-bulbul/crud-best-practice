package com.bulbul.bestpractice.area;


import com.bulbul.bestpractice.common.generic.entity.AbstractDomainBasedEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Area Entity
 * @author  Ashraful;
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "areas")
public class Area extends AbstractDomainBasedEntity {

    private String name;
    @Column(unique = true)
    private String code;
    private String description;
}
