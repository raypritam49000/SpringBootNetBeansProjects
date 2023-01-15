package com.parent.child.jpa.rest.api.repository;

import com.parent.child.jpa.rest.api.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
