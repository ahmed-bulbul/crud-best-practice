package com.bulbul.bestpractice.flightmanagement.entity;

import com.bulbul.bestpractice.user.entity.AssignUser;
import com.bulbul.bestpractice.common.generic.entity.AbstractDomainBasedEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "flight_info")
public class FlightInfo extends AbstractDomainBasedEntity {
    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer capacity;
    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer issued;
    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer checked;
    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer booked;
    @Column(name = "no_show", columnDefinition = "INTEGER DEFAULT 0")
    private Integer noShow;
    @OneToOne
    @JoinColumn(name = "fare_info_id")
    private FareInfo fareInfo;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "assign_user_id")
    private AssignUser assignUser;

}
