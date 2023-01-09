package com.brody.enterprisemanagement.mapping;

import com.brody.enterprisemanagement.dto.DepartmentDTO;
import com.brody.enterprisemanagement.dto.EmployeeDTO;
import com.brody.enterprisemanagement.dto.EnterpriseDTO;
import com.brody.enterprisemanagement.dto.MissionDTO;
import com.brody.enterprisemanagement.entities.*;

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

    Mission fromMissionDTO(MissionDTO missionDTO);
    MissionDTO fromMission(Mission mission);
    MissionDTO fromMission(MissionExternal mission);
    List<MissionDTO> fromListOfMissions(List<Mission> missions);

}
