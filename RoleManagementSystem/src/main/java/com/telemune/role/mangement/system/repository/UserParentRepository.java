package com.telemune.role.mangement.system.repository;

import com.telemune.role.mangement.system.entity.UserParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserParentRepository extends JpaRepository<UserParent, Long> {
}
