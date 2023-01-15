package com.telemune.junit.rest.api.integration;

import com.telemune.junit.rest.api.entity.Product;
import com.telemune.junit.rest.api.repository.ProductRepository;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    private Product laptop;
    private Product mobile;
    private Product tv;

    @Autowired
    private ProductRepository productRepository;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void beforeSetup() {
        System.out.println("BeforeEach init() method called");
        baseUrl = baseUrl + ":" + port + "/products";
        laptop = new Product("Laptop", "Hp Laptop", "This is Hp Laptop", "50000");
        mobile = new Product("Mobile", "Mi Mobile", "This is MI Mobile", "20000");
        tv = new Product("Tv", "Mi TV", "This is MI TV", "30000");

       laptop = productRepository.save(laptop);
       mobile = productRepository.save(mobile);
       tv = productRepository.save(tv);
    }

    @AfterEach
    public void afterSetup() {
        productRepository.deleteAll();
    }

    @Test
    void shouldCreateProductTest() {
        Product book = new Product("Book", "Java Book", "This is Java Book", "50000");
        Product newProduct = restTemplate.postForObject(baseUrl, book, Product.class);
        assertNotNull(newProduct);
        assertThat(newProduct.getId()).isNotNull();
    }

    @Test
    void shouldFetchProductsTest() {
        List<Product> list = restTemplate.getForObject(baseUrl, List.class);
        assertThat(list.size()).isEqualTo(3);
    }

    @Test
    void shouldFetchOneProductByIdTest() {
        Product existingProduct = restTemplate.getForObject(baseUrl + "/" + laptop.getId(), Product.class);
        assertNotNull(existingProduct);
        assertEquals("Laptop", existingProduct.getTitle());
    }

    @Test
    void shouldDeleteProductTest() {
        restTemplate.delete(baseUrl + "/" + mobile.getId());
        int count = productRepository.findAll().size();
        assertEquals(0, count);
    }

    @Test
    void shouldUpdateProductTest() {

        laptop.setTitle("Dell");
        laptop.setDescription("This is Dell Laptop");
        laptop.setPrice("50000");
        laptop.setName("Dell Laptop");

        restTemplate.put(baseUrl + "/{id}", laptop, laptop.getId());

        Product existingProduct = restTemplate.getForObject(baseUrl + "/" + laptop.getId(), Product.class);

        assertNotNull(existingProduct);
        assertEquals("Dell", existingProduct.getTitle());
    }

}
