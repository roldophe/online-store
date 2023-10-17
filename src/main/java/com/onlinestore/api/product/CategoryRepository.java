package com.onlinestore.api.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository
        extends JpaRepository<Category,Integer> {
    //Derived Query Method: automatically generate query
    //follow by name
    Optional<Category> findByName(String name);
}
