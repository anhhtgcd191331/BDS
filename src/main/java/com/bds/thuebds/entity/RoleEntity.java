package com.bds.thuebds.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "role",
        uniqueConstraints = @UniqueConstraint(name = "UniqueCodeAndRoleName", columnNames = {"roleCode", "roleName"}))
public class RoleEntity extends BaseEntity {
    @Column(name = "rolecode")
    @NotNull
    private String roleCode;

    @Column(name = "rolename")
    @NotNull
    private String roleName;

    @Column(name = "roledescription")
    private String roleDescription;

    @OneToMany(mappedBy = "role")
    List<UserRoleEntity> userRoleEntities;
}
