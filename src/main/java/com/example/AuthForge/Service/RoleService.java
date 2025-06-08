package com.example.AuthForge.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AuthForge.Model.Role;
import com.example.AuthForge.Repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Role not found with id " + id));
    }

    public Role createRole(Role role) {
        if (roleRepository.existsByName(role.getName())) {
            throw new RuntimeException("Role name already exists");
        }
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, Role roleDetails) {
        Role existingRole = roleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Role not found with id " + id));

        if (!existingRole.getName().equals(roleDetails.getName()) 
                && roleRepository.existsByName(roleDetails.getName())) {
            throw new RuntimeException("Role name already exists");
        }

        existingRole.setName(roleDetails.getName());
        existingRole.setDescription(roleDetails.getDescription());

        return roleRepository.save(existingRole);
    }

    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Role not found with id " + id));
        roleRepository.delete(role);
    }
}
