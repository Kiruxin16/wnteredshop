package ru.geekbrains.wnteredshop.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.wnteredshop.auth.entities.Role;
import ru.geekbrains.wnteredshop.auth.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private static RoleRepository roleRepository;

    public Role getRoleById(Long id){
        return roleRepository.getById(id);
    }
}
