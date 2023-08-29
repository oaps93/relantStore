package com.relantstore.pservice.Controller;

import com.relantstore.pservice.Service.ProductService;
import com.relantstore.pservice.Entity.Product;
import com.relantstore.pservice.Entity.Category;
import com.relantstore.pservice.Error.ErrorMessage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Product productSaved = this.productService.createProduct(product);
        return new ResponseEntity<>(productSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> createProduct(@PathVariable Long id, @RequestBody Product product){
        product.setId(id);
        Product productUpdated = this.productService.updateProduct(product);
        if(productUpdated == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(productUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        Product productDeleted = this.productService.deleteProduct(id);
        if(productDeleted == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(productDeleted,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updatePriceAndOrStock(@PathVariable Long id, @RequestParam(required = false) Double price, @RequestParam(required = false) Integer stock){
        Product productUpdated = this.productService.updateStockOrAndPrice(id, stock, price);
        if(productUpdated == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(productUpdated,HttpStatus.ACCEPTED);
    }

    private String formatMessage(BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map( e -> {
                    Map<String,String> errorMap = new HashMap<>();
                    errorMap.put(e.getField(), e.getDefaultMessage());
                    return errorMap;
                })
                .collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";
        try{
            jsonString = objectMapper.writeValueAsString(errorMessage);

        } catch (JsonProcessingException jpe){
            jpe.printStackTrace();

        }
        return jsonString;
    }

}
