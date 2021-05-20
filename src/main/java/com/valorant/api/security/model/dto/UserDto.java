package com.valorant.api.security.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String names;
    private String surnames;
    private String email;

    @Column(unique = true)
    private String username;
    private String password;
}
