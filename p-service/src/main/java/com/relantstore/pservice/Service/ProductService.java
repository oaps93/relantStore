package com.relantstore.pservice.Service;

import com.relantstore.pservice.Entity.Category;
import com.relantstore.pservice.Entity.Product;
import com.relantstore.pservice.Repository.ProductRepository;
import com.relantstore.pservice.Entity.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ProductService implements ProductServiceInterface {

    final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        product.setStatus(Status.AVAILABLE);
        product.setCreatedAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product productToUpdate = getProduct(product.getId());
        if (productToUpdate == null) return null;

        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setStock(product.getStock());
        productToUpdate.setStatus(product.getStatus());

        return productRepository.save(productToUpdate);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productToUpdate = getProduct(id);
        if (productToUpdate == null) return null;
        productToUpdate.setStatus(Status.DISCONTINUE);
        return productRepository.save(productToUpdate);
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStockOrAndPrice(Long id, Integer stock, Double price) {
        Product productToUpdate = getProduct(id);
        if (productToUpdate == null) return null;
        if (stock != null) {
            productToUpdate.setStock(productToUpdate.getStock() + stock);
        }
        if (price != null) {
            productToUpdate.setPrice(price);
        }
        return productRepository.save(productToUpdate);
    }

}


