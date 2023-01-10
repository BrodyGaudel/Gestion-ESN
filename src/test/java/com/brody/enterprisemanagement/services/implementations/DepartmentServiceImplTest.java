package com.brody.enterprisemanagement.services.implementations;

import com.brody.enterprisemanagement.dto.DepartmentDTO;
import com.brody.enterprisemanagement.dto.DepartmentsDTO;
import com.brody.enterprisemanagement.entities.Department;
import com.brody.enterprisemanagement.entities.Enterprise;
import com.brody.enterprisemanagement.exceptions.DepartmentNotFoundException;
import com.brody.enterprisemanagement.exceptions.EnterpriseNotFoundException;
import com.brody.enterprisemanagement.repositories.DepartmentRepository;
import com.brody.enterprisemanagement.repositories.EnterpriseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class DepartmentServiceImplTest {

    @Autowired
    private DepartmentServiceImpl service;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EnterpriseRepository enterpriseRepository;


    @Test
    void findById() throws DepartmentNotFoundException {
        Enterprise e = enterpriseRepository.save(new Enterprise(null, "Google", "SA",null));
        Department d = departmentRepository.save(new Department(null, "Hardware", e, null, null));
        DepartmentDTO departmentDTO = service.findById(d.getId());
        assertNotNull(departmentDTO);
        assertEquals(departmentDTO.getId(), d.getId());
    }

    @Test
    void findByEnterpriseId() throws EnterpriseNotFoundException {
        Enterprise e = enterpriseRepository.save(new Enterprise(null, "Google", "SA",null));
        List<DepartmentDTO> departmentDTOList = service.findByEnterpriseId(e.getId());
        assertNotNull(departmentDTOList);
    }

    @Test
    void findAll() {
        Enterprise e = enterpriseRepository.save(new Enterprise(null, "Google", "SA",null));
        departmentRepository.save(new Department(null, "Hardware", e, null, null));
        List<DepartmentDTO> departmentDTOList = service.findAll();
        DepartmentDTO departmentDTO = departmentDTOList.get(0);
        assertNotNull(departmentDTOList);
        assertNotNull(departmentDTO);
    }

    @Test
    void save() throws EnterpriseNotFoundException {
        Enterprise e = enterpriseRepository.save(new Enterprise(null, "Google", "SA",null));
        DepartmentDTO department = service.save(new DepartmentDTO(null, "Software", e.getId()));
        assertNotNull(department);
    }

    @Test
    void update() throws EnterpriseNotFoundException, DepartmentNotFoundException {
        Enterprise e = enterpriseRepository.save(new Enterprise(null, "Google", "SA",null));
        Department d = departmentRepository.save(new Department(null, "Hardware", e, null, null));
        DepartmentDTO department = service.update(new DepartmentDTO(d.getId(), "GCP", e.getId()));
        assertNotNull(department);
        assertEquals(d.getId(), department.getId());
    }

    @Test
    void deleteById() {
        Enterprise e = enterpriseRepository.save(new Enterprise(null, "Google", "SA",null));
        Department d = departmentRepository.save(new Department(null, "Hardware", e, null, null));
        service.deleteById(d.getId());
        Department department = departmentRepository.findById(d.getId()).orElse(null);
        assertNull(department);
    }

    @Test
    void deleteAll() {
        Enterprise e = enterpriseRepository.save(new Enterprise(null, "Google", "SA",null));
        Department d = departmentRepository.save(new Department(null, "Hardware", e, null, null));
        service.deleteAll();
        Department department = departmentRepository.findById(d.getId()).orElse(null);
        assertNull(department);
    }

    @Test
    void deleteAllByDepartmentId() throws EnterpriseNotFoundException {
        Enterprise e = enterpriseRepository.save(new Enterprise(null, "Google", "SA",null));
        Department d = departmentRepository.save(new Department(null, "Hardware", e, null, null));
        service.deleteAllByDepartmentId(e.getId());
        Department department = departmentRepository.findById(d.getId()).orElse(null);
        assertNull(department);
    }

    @Test
    void getDepartments() throws EnterpriseNotFoundException {
        Enterprise e = enterpriseRepository.save(new Enterprise(null, "Google", "SA",null));
        Department d = departmentRepository.save(new Department(null, "Hardware", e, null, null));
        DepartmentsDTO departmentDTOS = service.getDepartments(e.getId(),1,1);
        assertNotNull(departmentDTOS);
    }
}