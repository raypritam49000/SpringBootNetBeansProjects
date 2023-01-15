package com.ecommerce.rest.api.repository;

import com.ecommerce.rest.api.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    public abstract Optional<User> findByUsername(String name);
}
