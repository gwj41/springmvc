package com.robbie.mvc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
public class Dealership implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEALERSHIP_ID")
    private Long id;
    @Column(length = 20)
    private String name;
    @Embedded
    private Address address;
    @Column(length = 20)
    private String brand;
    @Column(length = 20)
    private String phoneNumber;
}
