package com.ecommerce.rest.api.validation;

import com.ecommerce.rest.api.dto.ProductDto;
import com.ecommerce.rest.api.payload.UserDto;
import org.springframework.stereotype.Component;

@Component
public class ParameterValidator {

    public boolean validate(ProductDto productDto) {
        return productDto != null && productDto.getTitle() != null && !productDto.getTitle().equals("")
                && productDto.getPrice() >= 0 && productDto.getUrl() != null && !productDto.getUrl().equals("")
                && productDto.getDescription() != null && !productDto.getDescription().equals("");
    }

    public boolean validateForLogin(UserDto userDto) {
        return userDto != null && userDto.getUsername() != null && !userDto.getUsername().equals("")
                && userDto.getPassword()!= null && !userDto.getPassword().equals("");
    }
}
