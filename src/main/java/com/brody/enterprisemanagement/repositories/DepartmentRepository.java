package com.brody.enterprisemanagement.repositories;

import com.brody.enterprisemanagement.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByName(String name);

    Page<Department> findByEnterpriseIdOrderByNameDesc(Long enterpriseId, Pageable pageable);
}
