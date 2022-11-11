package com.bulbul.bestpractice.user.entity;

import com.bulbul.bestpractice.common.generic.entity.AbstractDomainBasedEntity;
import com.bulbul.bestpractice.fgtmanagement.entity.Agency;
import com.bulbul.bestpractice.fgtmanagement.entity.FareInfo;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "assign_users")
public class AssignUser extends AbstractDomainBasedEntity {
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "agency_id")
    private Agency agency;
    @Column(name = "agency_id", insertable = false, updatable = false)
    private Long agencyId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fare_info_id")
    private FareInfo fareInfo;
    @Column(name = "fare_info_id", insertable = false, updatable = false)
    private Long fareInfoId;
}
