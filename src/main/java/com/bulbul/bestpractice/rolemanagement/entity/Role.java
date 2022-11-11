package com.bulbul.bestpractice.rolemanagement.entity;

import com.bulbul.bestpractice.common.generic.entity.AbstractDomainBasedEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@Table(name = "roles")
public class Role extends AbstractDomainBasedEntity {
    @Column(unique = true, nullable = false)
    private String name;

    public Role( String name) {
        this.name=name;
    }
}
