package com.example.AuthForge.Dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;

    private String password;
    private Long roleId;
    private String roleName;
    private boolean active = true;
}
