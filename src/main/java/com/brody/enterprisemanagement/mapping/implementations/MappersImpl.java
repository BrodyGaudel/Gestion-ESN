package com.brody.enterprisemanagement.mapping.implementations;

import com.brody.enterprisemanagement.dto.DepartmentDTO;
import com.brody.enterprisemanagement.dto.EmployeeDTO;
import com.brody.enterprisemanagement.dto.EnterpriseDTO;
import com.brody.enterprisemanagement.dto.MissionDTO;
import com.brody.enterprisemanagement.entities.*;
import com.brody.enterprisemanagement.mapping.Mappers;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MappersImpl implements Mappers {
    @Override
    public EnterpriseDTO fromEnterprise(Enterprise enterprise) {
        try{
            EnterpriseDTO enterpriseDTO = new EnterpriseDTO();
            enterpriseDTO.setId(enterprise.getId());
            enterpriseDTO.setName(enterprise.getName());
            enterpriseDTO.setRaisonSocial(enterprise.getRaisonSocial());
            return enterpriseDTO;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Enterprise fromEnterpriseDTO(EnterpriseDTO enterpriseDTO) {
        try{
            Enterprise enterprise = new Enterprise();
            enterprise.setId(enterpriseDTO.getId());
            enterprise.setName(enterpriseDTO.getName());
            enterprise.setRaisonSocial(enterpriseDTO.getRaisonSocial());
            return enterprise;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<EnterpriseDTO> fromListOfEnterprises(List<Enterprise> enterprises) {
        try{
            return enterprises.stream().map(this::fromEnterprise).toList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }

    @Override
    public DepartmentDTO fromDepartment(Department department) {
        try{
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(department.getId());
            departmentDTO.setName(department.getName());
            departmentDTO.setEnterpriseId(department.getEnterprise().getId());
            return departmentDTO;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Department fromDepartmentDTO(DepartmentDTO departmentDTO) {
        try{
            Department department = new Department();
            department.setId(departmentDTO.getId());
            department.setName(departmentDTO.getName());

            Enterprise enterprise = new Enterprise();
            enterprise.setId(departmentDTO.getEnterpriseId());
            department.setEnterprise(enterprise);
            return department;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<DepartmentDTO> fromListOfDepartments(List<Department> departments) {
        try{
            return departments.stream().map(this::fromDepartment).toList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }

    @Override
    public Employee fromEmployeeDTO(EmployeeDTO employeeDTO) {
        try{
            Employee employee = new Employee();
            employee.setName(employeeDTO.getName());
            employee.setFirstname(employeeDTO.getFirstname());
            employee.setEmail(employeeDTO.getEmail());
            employee.setIsActif(employeeDTO.getIsActif());
            employee.setContract(employeeDTO.getContract());
            employee.setRole(employeeDTO.getRole());
            employee.setId(employeeDTO.getId());

            return employee;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public EmployeeDTO fromEmployee(Employee employee) {
        try{
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setName(employee.getName());
            employeeDTO.setFirstname(employee.getFirstname());
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setIsActif(employee.getIsActif());
            employeeDTO.setRole(employee.getRole());
            employeeDTO.setContract(employee.getContract());
            return employeeDTO;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public List<EmployeeDTO> fromListOfEmployees(List<Employee> employees) {
        try{
            return employees.stream().map(this::fromEmployee).toList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }

    @Override
    public Mission fromMissionDTO(MissionDTO missionDTO) {
        try{
            Department department = new Department();
            department.setId(missionDTO.getDepartmentId());

            if(missionDTO.getEmailBilling() == null || missionDTO.getAverageDailyRate()==null){
                Mission mission = new Mission();
                mission.setDepartment(department);
                mission.setName(missionDTO.getName());
                mission.setDescription(missionDTO.getDescription());
                return mission;
            }else {
                MissionExternal mission = new MissionExternal();
                mission.setDepartment(department);
                mission.setName(missionDTO.getName());
                mission.setDescription(missionDTO.getDescription());
                mission.setAverageDailyRate(missionDTO.getAverageDailyRate());
                mission.setEmailBilling(mission.getEmailBilling());
                return mission;
            }


        }catch (Exception e){
            return null;
        }
    }

    @Override
    public MissionDTO fromMission(Mission mission) {
        try{
            MissionDTO missionDTO = new MissionDTO();
            missionDTO.setId(mission.getId());
            missionDTO.setName(mission.getName());
            missionDTO.setDescription(mission.getDescription());
            missionDTO.setDepartmentId(mission.getDepartment().getId());
            if(mission instanceof MissionExternal missionExternal){
                missionDTO.setEmailBilling(missionExternal.getEmailBilling());
                missionDTO.setAverageDailyRate(missionExternal.getAverageDailyRate());
            }
            return missionDTO;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public MissionDTO fromMission(MissionExternal mission) {
        try{
            MissionDTO missionDTO = new MissionDTO();
            missionDTO.setId(mission.getId());
            missionDTO.setName(mission.getName());
            missionDTO.setEmailBilling(mission.getEmailBilling());
            missionDTO.setAverageDailyRate(mission.getAverageDailyRate());
            missionDTO.setDescription(mission.getDescription());

            return missionDTO;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<MissionDTO> fromListOfMissions(List<Mission> missions) {
        try{
            return missions.stream().map(this::fromMission).toList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }
}
