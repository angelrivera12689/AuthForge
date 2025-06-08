package com.example.AuthForge.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleDto {
    private Long id;
    private String name;
    private String description;
}
