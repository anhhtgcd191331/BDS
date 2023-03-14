package com.bds.thuebds.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "appuser", uniqueConstraints = @UniqueConstraint(name = "UniqueUsername", columnNames = {"username"}))
public class UserEntity extends BaseEntity {
    @Column(name = "username")
    @Length(max = 16, min = 4)
    @NotNull
    private String username;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "fullname")
    @NotNull
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "isenable")
    @ColumnDefault("true")
    private boolean isEnable;

    @OneToMany(mappedBy = "user")
    List<UserRoleEntity> userRoleEntityList;
}
