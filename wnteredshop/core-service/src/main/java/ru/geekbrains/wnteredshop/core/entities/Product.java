package ru.geekbrains.wnteredshop.core.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name ="title")
    private String title;

    @Column(name ="price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @CreationTimestamp
    @Column(name ="created_at")
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(name ="updated_at" )
    private LocalDateTime updatedAt;
}
