package ru.geekbrains.wnteredshop.core.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;

    @Column(name = "address")
    private String address;

    @Column(name ="phone")
    private String phone;

    @Column(name = "total_price")
    private int totalPrice;

    @CreationTimestamp
    @Column(name ="created_at")
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(name ="updated_at" )
    private LocalDateTime updatedAt;
}