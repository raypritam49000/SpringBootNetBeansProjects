package com.telemune.junit.rest.api.service;

import com.telemune.junit.rest.api.entity.Product;
import java.util.List;

public interface ProductService {
    public abstract Product save(Product product);
    public abstract List<Product> getAllProducts();
    public abstract Product getProductById(int id);
    public abstract Product updateProduct(Product product, int id);
    public abstract void deleteProduct(int id);
}
