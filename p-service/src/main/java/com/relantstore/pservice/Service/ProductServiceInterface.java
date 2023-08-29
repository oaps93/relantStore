package com.relantstore.pservice.Service;

import com.relantstore.pservice.Entity.Product;
import com.relantstore.pservice.Entity.Category;

import java.util.List;

public interface ProductServiceInterface {
    List<Product> getAllProducts();
    Product getProduct(Long id);
    Product createProduct(Product product);
    Product updateProduct(Product product);
    Product deleteProduct(Long id);
    List<Product> getProductsByCategory(Category category);
    Product updateStockOrAndPrice(Long id, Integer qty, Double price);

}
