package com.example.AuthForge.Dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PageDto {
    private Long id;
    private String name;
    private String path;
    private String description;
    // Si quieres incluir los IDs de los permisos asociados (opcional)
    private List<Long> permissionIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
