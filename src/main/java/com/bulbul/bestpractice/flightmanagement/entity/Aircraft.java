package com.bulbul.bestpractice.flightmanagement.entity;

import com.bulbul.bestpractice.common.generic.entity.AbstractDomainBasedEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "aircraft")
public class Aircraft extends AbstractDomainBasedEntity {
    @Column(nullable = false, unique = true)
    private String code;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "agency_id")
    private Agency agency;
    @Column(name = "agency_id", insertable = false, updatable = false)
    private Long agencyId;

}
