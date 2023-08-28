package com.store.relantStore.Service;

import com.store.relantStore.Entity.Category;
import com.store.relantStore.Entity.Product;

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
