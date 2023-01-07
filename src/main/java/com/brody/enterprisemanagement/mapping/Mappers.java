package com.brody.enterprisemanagement.mapping;

import com.brody.enterprisemanagement.dto.DepartmentDTO;
import com.brody.enterprisemanagement.dto.EmployeeDTO;
import com.brody.enterprisemanagement.dto.EnterpriseDTO;
import com.brody.enterprisemanagement.entities.Department;
import com.brody.enterprisemanagement.entities.Employee;
import com.brody.enterprisemanagement.entities.Enterprise;

import java.util.List;

public interface Mappers {
    EnterpriseDTO fromEnterprise(Enterprise enterprise);
    Enterprise fromEnterpriseDTO(EnterpriseDTO enterpriseDTO);
    List<EnterpriseDTO> fromListOfEnterprises(List<Enterprise> enterprises);
    DepartmentDTO fromDepartment(Department department);
    Department fromDepartmentDTO(DepartmentDTO departmentDTO);
    List<DepartmentDTO> fromListOfDepartments(List<Department> departments);
    Employee fromEmployeeDTO(EmployeeDTO employeeDTO);
    EmployeeDTO fromEmployee(Employee employee);
    List<EmployeeDTO> fromListOfEmployees(List<Employee> employees);

}
