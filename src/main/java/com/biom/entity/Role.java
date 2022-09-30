package com.biom.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles", schema = "biom")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role() {}

    public Role(ERole name) {
        this.name = name;
    }

}
