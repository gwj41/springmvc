package com.robbie.mvc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class Address implements Serializable {
    @Column(length = 20)
    private String street;
    @Column(length = 20)
    private String city;
    @Column(length = 20)
    private String state;
    @Column(length = 20)
    private String zip;
}
