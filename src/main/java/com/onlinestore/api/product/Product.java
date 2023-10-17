package com.onlinestore.api.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String uuid;
    @Column(name = "pro_code", length = 30, unique = true, nullable = false)
    private String code;
    @Column(unique = true,length = 50, nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String image;
    @ManyToOne //many products to one category
    @JoinColumn(name = "cate_id")
    private Category category;
}
