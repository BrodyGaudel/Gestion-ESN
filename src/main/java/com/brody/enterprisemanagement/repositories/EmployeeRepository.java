package com.brody.enterprisemanagement.repositories;

import com.brody.enterprisemanagement.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
