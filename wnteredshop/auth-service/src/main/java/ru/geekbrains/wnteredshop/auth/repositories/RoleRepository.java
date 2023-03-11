package ru.geekbrains.wnteredshop.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.wnteredshop.auth.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
