package com.bulbul.bestpractice.user.entity;

import com.bulbul.bestpractice.common.generic.entity.AbstractDomainBasedEntity;
import com.bulbul.bestpractice.rolemanagement.entity.Role;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User extends AbstractDomainBasedEntity {


    private String username;
    private String password;
    private String code;
    @OneToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;
    @Column(name = "user_profile_id", insertable = false, updatable = false)
    private Long userProfileId;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Role> roles = new HashSet<>();
    private Long roleId;


    private boolean enabled;

}
