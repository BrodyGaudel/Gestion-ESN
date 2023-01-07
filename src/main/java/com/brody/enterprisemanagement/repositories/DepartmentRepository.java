package com.brody.enterprisemanagement.repositories;

import com.brody.enterprisemanagement.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByName(String name);
}
