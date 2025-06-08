package com.example.AuthForge.Controller;

import com.example.AuthForge.Dto.ApiResponseDto;
import com.example.AuthForge.Model.User;
import com.example.AuthForge.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Obtener todos los usuarios activos (GET: mostrar data)
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponseDto<>("Usuarios activos encontrados", users));
    }

    // Obtener usuario por ID (GET: mostrar data)
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(new ApiResponseDto<>("Usuario encontrado", user));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto<>("Usuario no encontrado", null));
        }
    }

    // Crear nuevo usuario (POST: solo mensaje)
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
            return ResponseEntity.ok(new ApiResponseDto<>("Usuario registrado exitosamente", null));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto<>(e.getMessage(), null));
        }
    }

    // Actualizar usuario (PUT: solo mensaje)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            userService.updateUser(id, userDetails);
            return ResponseEntity.ok(new ApiResponseDto<>("Usuario actualizado correctamente", null));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto<>(e.getMessage(), null));
        }
    }

    // Desactivar usuario (DELETE: solo mensaje)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deactivateUser(id);
            return ResponseEntity.ok(new ApiResponseDto<>("Usuario desactivado correctamente", null));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto<>(e.getMessage(), null));
        }
    }
}
