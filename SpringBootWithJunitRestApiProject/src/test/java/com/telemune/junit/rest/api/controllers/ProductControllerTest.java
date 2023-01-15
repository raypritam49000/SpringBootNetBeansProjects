package com.telemune.junit.rest.api.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telemune.junit.rest.api.entity.Product;
import com.telemune.junit.rest.api.service.impl.ProductServiceImpl;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@WebMvcTest
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
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
    void shouldCreateNewProduct() throws Exception {
        when(productService.save(any(Product.class))).thenReturn(laptop);
        this.mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(laptop)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(laptop.getId())))
                .andExpect(jsonPath("$.name", is(laptop.getName())))
                .andExpect(jsonPath("$.title", is(laptop.getTitle())))
                .andExpect(jsonPath("$.description", is(laptop.getDescription())))
                .andExpect(jsonPath("$.price", is(laptop.getPrice())));
    }

    @Test
    void shouldFetchAllProducts() throws Exception {
        List<Product> list = new ArrayList<>();
        list.add(laptop);
        list.add(tv);
        list.add(mobile);
        when(productService.getAllProducts()).thenReturn(list);
        this.mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(list.size())));
    }

    @Test
    void shouldFetchOneProductById() throws Exception {
        when(productService.getProductById(anyInt())).thenReturn(laptop);
        this.mockMvc.perform(get("/products/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(laptop.getId())))
                .andExpect(jsonPath("$.name", is(laptop.getName())))
                .andExpect(jsonPath("$.title", is(laptop.getTitle())))
                .andExpect(jsonPath("$.description", is(laptop.getDescription())))
                .andExpect(jsonPath("$.price", is(laptop.getPrice())));
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(anyInt());
        this.mockMvc.perform(delete("/products/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldUpdateProduct() throws Exception {
        when(productService.updateProduct(any(Product.class), anyInt())).thenReturn(laptop);
        this.mockMvc.perform(put("/products/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(laptop)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(laptop.getId())))
                .andExpect(jsonPath("$.name", is(laptop.getName())))
                .andExpect(jsonPath("$.title", is(laptop.getTitle())))
                .andExpect(jsonPath("$.description", is(laptop.getDescription())))
                .andExpect(jsonPath("$.price", is(laptop.getPrice())));
    }

}
