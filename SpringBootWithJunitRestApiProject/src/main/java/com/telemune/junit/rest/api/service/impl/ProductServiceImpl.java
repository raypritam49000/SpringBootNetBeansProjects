package com.telemune.junit.rest.api.service.impl;

import com.telemune.junit.rest.api.entity.Product;
import com.telemune.junit.rest.api.repository.ProductRepository;
import com.telemune.junit.rest.api.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Product found for the id " + id);
        });
    }

    @Override
    public Product updateProduct(Product product, int id) {
        Product existingProduct = productRepository.findById(id).get();
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setTitle(product.getTitle());
        existingProduct.setDescription(product.getDescription());
        return this.productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(int id) {
       this.productRepository.delete(productRepository.findById(id).get());
    }

}
