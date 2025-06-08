package com.example.AuthForge.Controller;

import com.example.AuthForge.Dto.ApiResponseDto;
import com.example.AuthForge.Model.Role;
import com.example.AuthForge.Service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // Obtener todos los roles
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<Role>>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(new ApiResponseDto<>("Roles encontrados", roles));
    }

    // Obtener rol por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        try {
            Role role = roleService.getRoleById(id);
            return ResponseEntity.ok(new ApiResponseDto<>("Rol encontrado", role));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto<>(e.getMessage(), null));
        }
    }
    // Crear nuevo rol
  @PostMapping
public ResponseEntity<?> createRole(@RequestBody Role role) {
    try {
        roleService.createRole(role);  // aquí no asignamos la variable
        return ResponseEntity.ok(new ApiResponseDto<>("Rol creado exitosamente", null));
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(new ApiResponseDto<>(e.getMessage(), null));
    }
}

    // Actualizar rol
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody Role roleDetails) {
        try {
            roleService.updateRole(id, roleDetails);
            return ResponseEntity.ok(new ApiResponseDto<>("Rol actualizado correctamente", null));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto<>(e.getMessage(), null));
        }
    }

    // Eliminar rol
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        try {
            roleService.deleteRole(id);
            return ResponseEntity.ok(new ApiResponseDto<>("Rol eliminado correctamente", null));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto<>(e.getMessage(), null));
        }
    }
}
