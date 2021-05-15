package com.valorant.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(length = 500)
    private String biography;
    private String image;
    @ManyToOne
    private Role role;
    @ManyToOne
    private Country country;
    @ManyToMany
    private Set<Ability> abilities;
}
