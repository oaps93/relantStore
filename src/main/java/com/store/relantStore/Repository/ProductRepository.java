package com.store.relantStore.Repository;

import com.store.relantStore.Entity.Category;
import com.store.relantStore.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
}
