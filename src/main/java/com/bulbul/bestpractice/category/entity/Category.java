package com.bulbul.bestpractice.category.entity;


import com.bulbul.bestpractice.common.generic.entity.AbstractDomainBasedEntity;
import lombok.*;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category extends AbstractDomainBasedEntity {


    private String name;
    private String description;
    private String code;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category category)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        return getId() != null ? getId().equals(category.getId()) : category.getId() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        return result;
    }

}
