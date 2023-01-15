package com.ecommerce.rest.api.service.impl;

import com.ecommerce.rest.api.dto.ProductDto;
import com.ecommerce.rest.api.entity.Product;
import com.ecommerce.rest.api.repository.ProductRepository;
import com.ecommerce.rest.api.service.ProductService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = this.modelMapper.map(productDto, Product.class);
        Product createProduct = this.productRepository.save(product);
        return this.modelMapper.map(createProduct, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            optionalProduct.get().setDescription(productDto.getDescription());
            optionalProduct.get().setPrice(productDto.getPrice());
            optionalProduct.get().setTitle(productDto.getTitle());
            optionalProduct.get().setUrl(productDto.getUrl());
            Product updatedProduct = productRepository.save(optionalProduct.get());
            return modelMapper.map(updatedProduct, ProductDto.class);
        } else {
            throw new IllegalArgumentException("Product Not Found with id : " + id);
        }
    }

    @Override
    public ProductDto getProduct(Long id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return modelMapper.map(optionalProduct.get(), ProductDto.class);
        } else {
            throw new IllegalArgumentException("Product Not Found with id : " + id);
        }
    }

    @Override
    public List<ProductDto> getProducts() {
        List<Product> productList = this.productRepository.findAll();
        List<ProductDto> productDtoList = productList.stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return productDtoList;
    }

    @Override
    public Boolean deleteProduct(Long id) {
        Optional<Product> optionalStudent = this.productRepository.findById(id);
        if (optionalStudent.isPresent()) {
            this.productRepository.delete(optionalStudent.get());
            return true;
        } else {
            return false;
        }
    }

}
