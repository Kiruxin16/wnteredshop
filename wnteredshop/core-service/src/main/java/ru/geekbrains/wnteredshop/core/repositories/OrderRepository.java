package ru.geekbrains.wnteredshop.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.wnteredshop.core.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    Optional<Order> findByUsername(String username);

    List<Order> findAllByUsername(String username);

}
