package com.bulbul.bestpractice.flightmanagement.entity;

import com.bulbul.bestpractice.common.generic.entity.AbstractDomainBasedEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "airport")
public class Airport extends AbstractDomainBasedEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String code;
}
