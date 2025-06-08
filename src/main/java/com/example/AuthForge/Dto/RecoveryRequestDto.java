package com.example.AuthForge.Dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RecoveryRequestDto {
    private Long id;
    private String token;
    private LocalDateTime expirationDate;
    private boolean used;

    private Long userId; // Solo el ID, para mantenerlo ligero

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
