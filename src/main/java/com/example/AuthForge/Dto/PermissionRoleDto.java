package com.example.AuthForge.Dto;

import com.example.AuthForge.Model.PermissionRole.PermissionType;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PermissionRoleDto {
    private Long id;

    private Long roleId;
    private String roleName; // Opcional para mostrar info

    private Long pageId;
    private String pageName; // Opcional para mostrar info

    private PermissionType permissionType;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
