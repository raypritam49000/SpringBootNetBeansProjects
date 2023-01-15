package com.parent.child.rest.api.repository;

import com.parent.child.rest.api.entity.UserParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserParentRepository extends JpaRepository<UserParent, Long> {
    public abstract UserParent findByUsername(String username);
}
