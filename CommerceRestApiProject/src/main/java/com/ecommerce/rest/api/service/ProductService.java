package com.ecommerce.rest.api.service;

import com.ecommerce.rest.api.dto.ProductDto;
import java.util.List;

public interface ProductService {
    public ProductDto createProduct(ProductDto productDto);
    public ProductDto updateProduct(Long id,ProductDto productDto);
    public ProductDto getProduct(Long id);
    public List<ProductDto> getProducts();
    public Boolean deleteProduct(Long id);   
}
