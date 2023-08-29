package com.relantstore.pservice.ServiceTest;

import com.relantstore.pservice.Entity.Product;
import com.relantstore.pservice.Entity.Status;
import com.relantstore.pservice.Repository.ProductRepository;
import com.relantstore.pservice.Service.ProductService;
import com.relantstore.pservice.Service.ProductServiceInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    private ProductServiceInterface productServiceInterface;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        productServiceInterface = new ProductService(productRepository);
        Product productMock1 = Product.builder()
                .id(1L)
                .name("C4")
                .description("Mock c4 valve")
                .price(1500.13)
                .status(Status.AVAILABLE)
                .createdAt(new Date())
                .stock(10)
                .build();
        Mockito.when(this.productRepository.findById(productMock1.getId()))
                .thenReturn(Optional.of(productMock1));
        Mockito.when(this.productRepository.save(productMock1)).thenReturn(productMock1);
    }

    @Test
    public void whenValidId_ReturnProduct(){
        Product product = productServiceInterface.getProduct(1L);
        Assertions.assertEquals(product.getName(),"C4");
    }

    @Test
    public void whenUpdateStock_ReturnUpdatedStock(){
        Product updatedStock = productServiceInterface.updateStockOrAndPrice(1L,3, 13.3);
        Assertions.assertEquals(updatedStock.getStock(),13);
        Assertions.assertEquals(updatedStock.getPrice(),13.3);


    }
}
