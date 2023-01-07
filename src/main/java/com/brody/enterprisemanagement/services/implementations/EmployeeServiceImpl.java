package com.brody.enterprisemanagement.services.implementations;

import com.brody.enterprisemanagement.dto.EmployeeDTO;
import com.brody.enterprisemanagement.entities.Department;
import com.brody.enterprisemanagement.entities.Employee;
import com.brody.enterprisemanagement.exceptions.DepartmentNotFoundException;
import com.brody.enterprisemanagement.exceptions.EmployeeNotFoundException;
import com.brody.enterprisemanagement.mapping.Mappers;
import com.brody.enterprisemanagement.repositories.DepartmentRepository;
import com.brody.enterprisemanagement.repositories.EmployeeRepository;
import com.brody.enterprisemanagement.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final Mappers mappers;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, Mappers mappers) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.mappers = mappers;
    }


    @Override
    public EmployeeDTO findById(Long id) throws EmployeeNotFoundException {
        log.info("In findById()");
        Employee employee = employeeRepository.findById(id)
                .orElseThrow( ()-> new EmployeeNotFoundException("employee not found"));
        log.info("employee found");
        return mappers.fromEmployee(employee);
    }

    @Override
    public List<EmployeeDTO> findByFirstnameOrName(String keyword) {
        log.info("In findByFirstnameOrName()");
        List<Employee> employees1 = employeeRepository.findByFirstname(keyword);
        List<Employee> employees2 = employeeRepository.findByName(keyword);
        List<Employee> employees = new ArrayList<>();
        employees.addAll(employees1);
        employees.addAll(employees2);
        log.info("employee(s) found");
        return mappers.fromListOfEmployees(employees);
    }

    @Override
    public List<EmployeeDTO> findAll() {
        log.info("In findAll()");
        List<Employee> employees = employeeRepository.findAll();
        log.info("employee found");
        return mappers.fromListOfEmployees(employees);
    }

    @Override
    public List<EmployeeDTO> findByDepartmentId(Long id) {
        log.info("In findByDepartmentId()");
        List<Employee> employees = employeeRepository.findAll();
        List<Employee> employeeList = new ArrayList<>();
        List<Department> departments;
        for(Employee e: employees){
            departments = e.getDepartments();
            for(Department d: departments){
                if(d.getId().equals(id)){
                    employeeList.add(e);
                }
            }
        }
        log.info("employee found");
        return mappers.fromListOfEmployees(employeeList);
    }

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        log.info("In save()");
        Employee employee = mappers.fromEmployeeDTO(employeeDTO);
        if(employee == null){
            return null;
        }else {
            log.info(employee.toString());
            Employee employeeSaved = employeeRepository.save(employee);
            log.info("employee saved");
            return mappers.fromEmployee(employeeSaved);
        }
    }

    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO) throws EmployeeNotFoundException {
        log.info("In update()");
        Employee employee = employeeRepository.findById(employeeDTO.getId())
                .orElseThrow( () -> new EmployeeNotFoundException("employee that you want to update doesn't found"));

        employee.setName(employeeDTO.getName());
        employee.setFirstname(employeeDTO.getFirstname());
        employee.setIsActif(employeeDTO.getIsActif());
        employee.setRole(employeeDTO.getRole());
        employee.setContract(employeeDTO.getContract());

        Employee employeeUpdated = employeeRepository.save(employee);
        log.info("employee updated");
        return mappers.fromEmployee(employeeUpdated);
    }

    @Override
    public void deleteById(Long id) {
        log.info("In deleteById()");
        employeeRepository.deleteById(id);
        log.info("employee well deleted");
    }

    @Override
    public void deleteAll() {
        log.info("In deleteAll()");
        employeeRepository.deleteAll();
        log.info("All employees have been deleted");
    }

    @Override
    public Boolean addEmployeeToDepartment(Long idEmployee, Long idDepartment) throws EmployeeNotFoundException, DepartmentNotFoundException {
        log.info("In addEmployeeToDepartment()");
        Employee employee = employeeRepository.findById(idEmployee)
                .orElseThrow( ()-> new EmployeeNotFoundException("employee does not exist"));

        Department department = departmentRepository.findById(idDepartment)
                .orElseThrow( () -> new DepartmentNotFoundException("department does not exist"));

        List<Department> departments = employee.getDepartments();
        List<Employee> employees = department.getEmployees();

        departments.add(department);
        department.setEmployees(employees);
        employees.add(employee);
        employee.setDepartments(departments);

        employeeRepository.save(employee);
        departmentRepository.save(department);
        log.info("affectation well done");
        return true;
    }

    @Override
    public Boolean deleteEmployeeFromDepartment(Long idEmployee, Long idDepartment) throws EmployeeNotFoundException, DepartmentNotFoundException {
        Department department = departmentRepository.findById(idDepartment)
                .orElseThrow( () -> new DepartmentNotFoundException("department does not exist"));

        Employee employee = employeeRepository.findById(idEmployee)
                .orElseThrow( ()-> new EmployeeNotFoundException("employee does not exist"));

        List<Department> departments = employee.getDepartments();
        List<Employee> employees = department.getEmployees();

        if((!departments.isEmpty()) && (!employees.isEmpty())){

            departments.removeIf(d -> d.getId().equals(department.getId()));

            employees.removeIf(e -> e.getId().equals(employee.getId()));

            employee.setDepartments(departments);
            department.setEmployees(employees);

            employeeRepository.save(employee);
            departmentRepository.save(department);
            log.info("affectation well undone");
            return true;
        }else {
            log.info("affectation not well undone");
            return false;
        }

    }
}
