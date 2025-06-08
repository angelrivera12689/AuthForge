package com.example.AuthForge.Service;

import com.example.AuthForge.Model.User;
import com.example.AuthForge.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Crear nuevo usuario
    public User createUser(User user) {
        validatePassword(user.getPasswordHash());

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está en uso");
        }

        return userRepository.save(user);
    }

    // Actualizar usuario existente
    public User updateUser(Long id, User userDetails) {
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        if (!existingUser.isActive()) {
            throw new RuntimeException("No se puede modificar un usuario desactivado");
        }

        if (!existingUser.getEmail().equals(userDetails.getEmail())) {
            if (userRepository.existsByEmail(userDetails.getEmail())) {
                throw new RuntimeException("El correo electrónico ya está en uso");
            }
            existingUser.setEmail(userDetails.getEmail());
        }

        existingUser.setName(userDetails.getName());

        if (!existingUser.getPasswordHash().equals(userDetails.getPasswordHash())) {
            validatePassword(userDetails.getPasswordHash());
            existingUser.setPasswordHash(userDetails.getPasswordHash());
        }

        existingUser.setRole(userDetails.getRole());
        existingUser.setActive(userDetails.isActive());

        return userRepository.save(existingUser);
    }

    // Desactivar usuario (soft delete)
    public void deactivateUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        if (!user.isActive()) {
            throw new RuntimeException("El usuario ya está desactivado");
        }

        user.setActive(false);
        userRepository.save(user);
    }

    // Obtener usuario por ID solo si está activo
    public User getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        if (!user.isActive()) {
            throw new RuntimeException("El usuario está desactivado");
        }

        return user;
    }

    // Obtener todos los usuarios activos
    public List<User> getAllUsers() {
        return userRepository.findByActiveTrue();
    }

    // Validación de contraseña segura
    private void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new RuntimeException("La contraseña no puede estar vacía");
        }
        if (password.length() < 8) {
            throw new RuntimeException("La contraseña debe tener al menos 8 caracteres");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new RuntimeException("La contraseña debe contener al menos una letra mayúscula");
        }
        if (!password.matches(".*[a-z].*")) {
            throw new RuntimeException("La contraseña debe contener al menos una letra minúscula");
        }
        if (!password.matches(".*\\d.*")) {
            throw new RuntimeException("La contraseña debe contener al menos un número");
        }
        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            throw new RuntimeException("La contraseña debe contener al menos un carácter especial");
        }
    }
}
