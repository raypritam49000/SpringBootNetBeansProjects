package com.telemune.junit.rest.api.services;

import com.telemune.junit.rest.api.entity.Product;
import com.telemune.junit.rest.api.repository.ProductRepository;
import com.telemune.junit.rest.api.service.impl.ProductServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product laptop;
    private Product mobile;
    private Product tv;

    @BeforeEach
    void init() {
        System.out.println("BeforeEach init() method called");
        laptop = new Product("Laptop", "Hp Laptop", "This is Hp Laptop", "50000");
        mobile = new Product("Mobile", "Mi Mobile", "This is MI Mobile", "20000");
        tv = new Product("Tv", "Mi TV", "This is MI TV", "30000");
    }

    @Test
    void saveProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(laptop);
        Product newProduct = productService.save(laptop);
        assertNotNull(newProduct);
        assertThat(newProduct.getTitle()).isEqualTo("Laptop");
    }

    @Test
    void getProducts() {
        List<Product> list = new ArrayList<>();
        list.add(laptop);
        list.add(tv);
        list.add(mobile);

        when(productRepository.findAll()).thenReturn(list);
        List<Product> products = productService.getAllProducts();

        assertEquals(3, products.size());
        assertNotNull(products);
    }

    @Test
    void getProductById() {

        when(productRepository.findById(anyInt())).thenReturn(Optional.of(laptop));
        Product existingProduct = productService.getProductById(laptop.getId());
        assertNotNull(existingProduct);
        assertThat(existingProduct.getId()).isNotEqualTo(null);
    }

    @Test
    void getProductByIdForException() {

        when(productRepository.findById(1)).thenReturn(Optional.of(laptop));
        RuntimeException assertThrows = assertThrows(RuntimeException.class, () -> {
            productService.getProductById(laptop.getId());
        });
    }

    @Test
    void updateProduct() {

        when(productRepository.findById(anyInt())).thenReturn(Optional.of(laptop));
        when(productRepository.save(any(Product.class))).thenReturn(laptop);

        laptop.setTitle("OnePlus");

        Product exisitingProduct = productService.updateProduct(laptop, laptop.getId());

        assertNotNull(exisitingProduct);
        assertEquals("OnePlus", laptop.getTitle());
    }

    @Test
    void deleteProduct() {

        int productId = 1;
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(laptop));
        doNothing().when(productRepository).delete(any(Product.class));

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).delete(laptop);

    }
}
