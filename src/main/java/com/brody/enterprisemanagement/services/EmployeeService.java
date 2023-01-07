package com.brody.enterprisemanagement.services;

import com.brody.enterprisemanagement.dto.EmployeeDTO;
import com.brody.enterprisemanagement.exceptions.DepartmentNotFoundException;
import com.brody.enterprisemanagement.exceptions.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO findById(Long id) throws EmployeeNotFoundException;
    List<EmployeeDTO> findByFirstnameOrName(String keyword);
    List<EmployeeDTO> findAll();
    List<EmployeeDTO> findByDepartmentId(Long id);
    EmployeeDTO save(EmployeeDTO employeeDTO);
    EmployeeDTO update(EmployeeDTO employeeDTO) throws EmployeeNotFoundException;
    void deleteById(Long id);
    void deleteAll();
    Boolean addEmployeeToDepartment(Long idEmployee, Long idDepartment) throws EmployeeNotFoundException, DepartmentNotFoundException;
    Boolean deleteEmployeeFromDepartment(Long idEmployee, Long idDepartment) throws EmployeeNotFoundException, DepartmentNotFoundException;


}
