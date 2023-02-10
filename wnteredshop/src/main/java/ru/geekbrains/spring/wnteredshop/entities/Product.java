package ru.geekbrains.spring.wnteredshop.entities;

import lombok.Data;

import javax.persistence.*;

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
    private int price;
}
