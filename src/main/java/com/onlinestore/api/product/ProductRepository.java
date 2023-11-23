package com.onlinestore.api.product;

import com.onlinestore.api.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Boolean existsByUuid(String uuid);
    Optional<Product> findByUuid (String byUuid);
}
