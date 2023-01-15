package com.telemune.junit.rest.api.repository;

import com.telemune.junit.rest.api.entity.Product;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product laptop;
    private Product mobile;
    private Product tv;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("BeforeAll init() method called");
    }

    @BeforeEach
    void init() {
        System.out.println("BeforeEach init() method called");
        laptop = new Product("Laptop", "Hp Laptop", "This is Hp Laptop", "50000");
        mobile = new Product("Mobile", "Mi Mobile", "This is MI Mobile", "20000");
        tv = new Product("Tv", "Mi TV", "This is MI TV", "30000");
    }

    @BeforeEach
    public void initEach() {
        System.out.println("BeforeEach initEach() method called");
    }

    @Test
    @DisplayName("It should save the product to the database")
    void saveProduct() {
        System.out.println("It should save the product to the database");
        Product newProduct = productRepository.save(laptop);
        assertNotNull(newProduct);
        assertThat(newProduct.getId()).isNotEqualTo(null);
    }

    @Test
    @DisplayName("It should return the prodcts list with size of 3")
    void getAllProducts() {
        System.out.println("It should return the prodcts list with size of 3");
        productRepository.save(laptop);
        productRepository.save(mobile);
        productRepository.save(tv);

        List<Product> list = productRepository.findAll();

        assertNotNull(list);
        assertThat(list).isNotNull();
        assertEquals(3, list.size());
    }

    @Test
    @DisplayName("It should return the product by its id")
    void getProductById() {
        System.out.println("It should return the product by its id");

        Product createProduct = productRepository.save(laptop);

        Product newProduct = productRepository.findById(createProduct.getId()).get();

        assertNotNull(newProduct);
        assertEquals(createProduct.getId(), newProduct.getId());
    }

    @Test
    @DisplayName("It should delete the existing product")
    void deleteProduct() {
        System.out.println("It should delete the existing product");
        productRepository.save(laptop);
        int id = laptop.getId();
        productRepository.save(tv);
        productRepository.delete(laptop);

        List<Product> list = productRepository.findAll();

        Optional<Product> exitingProduct = productRepository.findById(id);

        assertEquals(1, list.size());
        assertThat(exitingProduct).isEmpty();
    }

}
