package com.shop.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role", uniqueConstraints = {
        @UniqueConstraint(columnNames = "roleName")
})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @ManyToMany(mappedBy = "roleSet",fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<User> userSet = new HashSet<>();

    public Role() {
    }

    public Role(Long id, RoleName roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}
