package com.telemune.rest.api.repository;

import com.telemune.rest.api.entity.Employee;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public abstract Optional<Employee> findByEmail(String email);
}
