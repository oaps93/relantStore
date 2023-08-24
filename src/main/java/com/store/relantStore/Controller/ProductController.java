package com.store.relantStore.Controller;

import com.store.relantStore.Entity.Category;
import com.store.relantStore.Entity.Product;
import com.store.relantStore.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> listProducts(@RequestParam(name="categoryId", required = false) Long categoryId){
        List<Product> productList = new ArrayList<>();

        if(categoryId == null){
            productList = this.productService.getAllProducts();
            if(productList.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        } else {
            productList = this.productService.getProductsByCategory(Category.builder().id(categoryId).build());
            if(productList.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }

        return new ResponseEntity<>(productList, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(name = "id") Long id){
        Product product = this.productService.getProduct(id);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product productSaved = this.productService.createProduct(product);
        return new ResponseEntity<>(productSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> createProduct(@RequestParam Long id, @RequestBody Product product){
        product.setId(id);
        Product productUpdated = this.productService.updateProduct(product);
        if(productUpdated == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(productUpdated, HttpStatus.OK);
    }

}
