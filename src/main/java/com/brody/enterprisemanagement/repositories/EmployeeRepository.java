package com.brody.enterprisemanagement.repositories;

import com.brody.enterprisemanagement.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select e from Employee e where e.name like :kw")
    List<Employee> findByName(@Param("kw") String keyword);

    @Query("select e from Employee e where e.firstname like :kw")
    List<Employee> findByFirstname(@Param("kw") String keyword);
}
