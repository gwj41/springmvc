package com.robbie.mvc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ldap.odm.annotations.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.naming.Name;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table
@XmlRootElement
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entry(objectClasses = {"inetOrgPerson", "organizationalPerson", "person", "top"}, base="ou=people")
public class User implements UserDetails,Serializable{
    public void showMyself() {
        System.out.println("===================I am com.robbie.mvc.entity.User==========================");
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    @org.springframework.ldap.odm.annotations.Transient
    private Long id;
    @Column(length = 20)
    @Attribute(name = "sn")
    private String firstName;
    @NotBlank(message = "You must input Last Name!")
    @Column(length = 20)
    @Attribute(name = "givenName")
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @org.springframework.ldap.odm.annotations.Transient
    private Date birthday;
    @Column(length = 3)
    @org.springframework.ldap.odm.annotations.Transient
    private int age;
    @Column(length = 20)
    @DnAttribute(value = "uid",index = 0)
    private String username;
    @Column(length = 20)
    @Attribute(name = "userPassword")
    private String password;
    @Column(length = 20,name = "role")
    @org.springframework.ldap.odm.annotations.Transient
    private String role;
    @Column(length = 20)
    @org.springframework.ldap.odm.annotations.Transient
    private String organizationUnit;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DEALERSHIP_ID")
    @org.springframework.ldap.odm.annotations.Transient
    private Dealership dealership;
    @Transient
    @org.springframework.ldap.odm.annotations.Transient
    private Collection<? extends GrantedAuthority> authorities;
    @CreatedBy
    @org.springframework.ldap.odm.annotations.Transient
    private String createdBy;
    @LastModifiedBy
    @org.springframework.ldap.odm.annotations.Transient
    private String lastModifiedBy;
    @CreatedDate()
    @org.springframework.ldap.odm.annotations.Transient
    private Date createdDate;
    @LastModifiedDate
    @org.springframework.ldap.odm.annotations.Transient
    private Date lastModifiedDate;
    @org.springframework.ldap.odm.annotations.Id
    private Name dn;

/*    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(this.role);
    }*/

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
