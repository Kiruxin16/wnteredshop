package ru.geekbrains.wnteredshop.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.wnteredshop.core.entities.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
