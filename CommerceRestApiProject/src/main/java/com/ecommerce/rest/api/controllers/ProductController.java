package com.ecommerce.rest.api.controllers;

import com.ecommerce.rest.api.dto.ProductDto;
import com.ecommerce.rest.api.response.ApiResponse;
import com.ecommerce.rest.api.service.ProductService;
import com.ecommerce.rest.api.validation.ParameterValidator;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ParameterValidator parameterValidator;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            List<ProductDto> productList = productService.getProducts();

            if (productList != null && !productList.isEmpty() && productList.size() > 0) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Product Details", true, productList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(404, "DATA_NOT_FOUND", "Product Details Not Found", false, Arrays.asList()), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", false, Arrays.asList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProduct(@PathVariable("id") long id) {
        try {

            if (id <= 0) {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "Please enter id is greater than 0", false, Arrays.asList()), HttpStatus.BAD_REQUEST);
            }

            ProductDto productDto = productService.getProduct(id);
            Boolean isValid = parameterValidator.validate(productDto);
            if (isValid) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Product Details", true, productDto), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(404, "DATA_NOT_FOUND", "Product Details Not Found", false, Arrays.asList()), HttpStatus.NOT_FOUND);
            }
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ApiResponse(404, "NOT_FOUND", "Products Detail Not Found", false, Arrays.asList()), HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", false, Arrays.asList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {
        try {
            Boolean isValid = parameterValidator.validate(productDto);
            if (isValid) {
                ProductDto createProduct = this.productService.createProduct(productDto);
                return new ResponseEntity<>(new ApiResponse(201, "CREATED", "Product Created", true, createProduct), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "All Parameter are required", false, Arrays.asList()), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", false, Arrays.asList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") long id) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "Please enter id is greater than 0", false, Arrays.asList()), HttpStatus.BAD_REQUEST);
            }

            Boolean isDeleted = productService.deleteProduct(id);

            if (isDeleted) {
                return new ResponseEntity<>(new ApiResponse(200, "SUCCESS", "Products Detail Deleted", true, Arrays.asList()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "Error Occur While Delete Product Details", false, Arrays.asList()), HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", false, Arrays.asList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateTransaction(@PathVariable("id") long id, @RequestBody ProductDto productDto) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "Please enter id is greater than 0", false, Arrays.asList()), HttpStatus.BAD_REQUEST);
            }

            Boolean isValid = parameterValidator.validate(productDto);
            if (isValid) {
                ProductDto updateProductDto = productService.updateProduct(id, productDto);
                return new ResponseEntity<>(new ApiResponse(203, "UPDATED", "Product Details updated", true, updateProductDto), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new ApiResponse(400, "BAD_REQUEST", "All Parameter are required", false, Arrays.asList()), HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "INTERNAL_SERVER_ERROR", "Server Error", false, Arrays.asList()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
