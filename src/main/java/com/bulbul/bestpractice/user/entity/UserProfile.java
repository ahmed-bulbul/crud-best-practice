package com.bulbul.bestpractice.user.entity;

import com.bulbul.bestpractice.common.generic.entity.AbstractDomainBasedEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_profiles")
public class UserProfile extends AbstractDomainBasedEntity {
    private String firstName;
    private String lastName;
    private String address;
    private String contactNumber;
}
