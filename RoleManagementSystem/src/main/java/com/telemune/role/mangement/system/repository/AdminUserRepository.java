package com.telemune.role.mangement.system.repository;

import com.telemune.role.mangement.system.entity.AdminUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Integer> {
    public Optional<AdminUser> findByUsername(String username);
    public Boolean existsByUsername(String username);
    public Boolean existsByEmail(String email);
    public void deleteByUsername(String username);
}
