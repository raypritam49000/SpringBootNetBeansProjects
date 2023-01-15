package com.telemune.role.mangement.system.repository;

import com.telemune.role.mangement.system.entity.HttpLinks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HttpLinksRepository extends JpaRepository<HttpLinks, Integer> {
    public HttpLinks findByLinkId(Integer id);
}
