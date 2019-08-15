package com.crud2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud2.exception.ResourceNotFoundException;
import com.crud2.model.Product;
import com.crud2.repository.ProductRepository;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId)
        throws ResourceNotFoundException {
        Product product = (Product) productRepository.findAll();
        return ResponseEntity.ok().body(product);
    }
    
    @PostMapping("/products")
    public Product createEmployee(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updatedProduct(@PathVariable(value = "id") Long productId,
         @Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
        Product product = (Product) productRepository.findAll();
        		//.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));

        product.setEmailId(productDetails.getEmailId());
        product.setLastName(productDetails.getLastName());
        product.setFirstName(productDetails.getFirstName());
        final Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long productId)
         throws ResourceNotFoundException {
    	Product product = (Product) productRepository.findAll();
    									//.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + employeeId));

        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
