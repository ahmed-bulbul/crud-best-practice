package com.bulbul.bestpractice.user.entity;

import com.bulbul.bestpractice.common.generic.entity.AbstractDomainBasedEntity;
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

}
