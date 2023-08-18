package com.store.relantStore.RepositoryTest;

import com.store.relantStore.Entity.Category;
import com.store.relantStore.Entity.Product;
import com.store.relantStore.Entity.Status;
import com.store.relantStore.Repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Test
    public void whenFindByCategory_thenReturnList(){
        Product product1 = Product.builder()
                .name("C3")
                .description("")
                .stock(10)
                .price(1500.15)
                .status(Status.AVAILABLE)
                .createdAt(new Date())
                .category(Category.builder().id(1L).build())
                .build();

        productRepository.save(product1);

        Assertions.assertEquals(3, productRepository.findByCategory(product1.getCategory()).size());

    }
}
