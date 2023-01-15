package com.ecommerce.rest.api.payload;

import com.ecommerce.rest.api.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtApiResponse {
    private String type;
    private String token;
    private User user;
}
