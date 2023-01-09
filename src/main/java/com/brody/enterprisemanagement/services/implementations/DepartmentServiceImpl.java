package com.brody.enterprisemanagement.services.implementations;

import com.brody.enterprisemanagement.dto.DepartmentDTO;
import com.brody.enterprisemanagement.dto.DepartmentsDTO;
import com.brody.enterprisemanagement.entities.Department;
import com.brody.enterprisemanagement.entities.Enterprise;
import com.brody.enterprisemanagement.exceptions.DepartmentNotFoundException;
import com.brody.enterprisemanagement.exceptions.EnterpriseNotFoundException;
import com.brody.enterprisemanagement.mapping.Mappers;
import com.brody.enterprisemanagement.repositories.DepartmentRepository;
import com.brody.enterprisemanagement.repositories.EnterpriseRepository;
import com.brody.enterprisemanagement.services.DepartmentService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final Mappers mappers;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EnterpriseRepository enterpriseRepository, Mappers mappers) {
        this.departmentRepository = departmentRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.mappers = mappers;
    }

    @Override
    public DepartmentDTO findById(Long id) throws DepartmentNotFoundException {
        log.info("In findById()");
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("department not found"));
        log.info("department found");
        return mappers.fromDepartment(department);
    }

    @Override
    public List<DepartmentDTO> findByEnterpriseId(Long id) throws EnterpriseNotFoundException {
        log.info("In findByEnterprise()");
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new EnterpriseNotFoundException("enterprise not found"));

        List<Department> departments = departmentRepository.findAll()
                .stream()
                .filter(department -> department.getEnterprise().getId().equals(enterprise.getId()))
                .toList();
        log.info("departments found");
        return mappers.fromListOfDepartments(departments);
    }

    @Override
    public List<DepartmentDTO> findAll() {
        log.info("In findAll()");
        List<Department> departments = departmentRepository.findAll();
        log.info("department(s) found");
        return mappers.fromListOfDepartments(departments);
    }

    @Override
    public DepartmentDTO save(DepartmentDTO departmentDTO) throws EnterpriseNotFoundException {
        log.info("In save()");
        Enterprise enterprise = enterpriseRepository.findById(departmentDTO.getEnterpriseId())
                .orElseThrow( () -> new EnterpriseNotFoundException("enterprise not found or have been deleted"));

        Department department = mappers.fromDepartmentDTO(departmentDTO);
        if(department != null && department.getName() != null){
            department.setEnterprise(enterprise);
            Department departmentSaved = departmentRepository.save(department);
            log.info("department saved");
            return mappers.fromDepartment(departmentSaved);
        }else {
            log.info("department not saved");
            return null;
        }

    }

    @Override
    public DepartmentDTO update(DepartmentDTO departmentDTO) throws DepartmentNotFoundException, EnterpriseNotFoundException {
        log.info("In update()");
        Department department = departmentRepository.findById(departmentDTO.getId())
                .orElseThrow( () -> new DepartmentNotFoundException("department that you want to updated is not found"));

        Enterprise enterprise = getEnterprise(departmentDTO.getEnterpriseId());

        department.setEnterprise(enterprise);
        department.setName(departmentDTO.getName());

        Department departmentUpdated = departmentRepository.save(department);
        log.info("department updated");
        return mappers.fromDepartment(departmentUpdated);
    }

    @Override
    public void deleteById(Long id) {
        log.info("In deleteById()");
        departmentRepository.deleteById(id);
        log.info("department deleted");
    }

    @Override
    public void deleteAll() {
        log.info("In deleteAll()");
        departmentRepository.deleteAll();
        log.info("departments deleted");
    }

    @Override
    public void deleteAllByDepartmentId(Long departmentId) throws EnterpriseNotFoundException {
        log.info("In deleteAllByDepartmentId()");
        List<DepartmentDTO> departmentDTOS = findByEnterpriseId(departmentId);
        for(DepartmentDTO d: departmentDTOS){
            deleteById(d.getId());
        }
        log.info("department(s) deleted");
    }

    @Override
    public DepartmentsDTO getDepartments(Long enterpriseId, int page, int size) throws EnterpriseNotFoundException {
        log.info("In getDepartments()");
        Enterprise enterprise = getEnterprise(enterpriseId);

        Page<Department> departmentPage = departmentRepository
                .findByEnterpriseIdOrderByNameDesc(enterpriseId, PageRequest.of(page, size));

        List<DepartmentDTO> departmentDTOS = mappers.fromListOfDepartments(departmentPage.getContent());

        DepartmentsDTO departmentsDTO = new DepartmentsDTO();
        departmentsDTO.setName(enterprise.getName());
        departmentsDTO.setEnterpriseId(enterprise.getId());
        departmentsDTO.setDepartmentDTOList(departmentDTOS);
        departmentsDTO.setPageSize(size);
        departmentsDTO.setTotalPages(departmentPage.getTotalPages());
        departmentsDTO.setCurrentPage(page);

        log.info("department(s) gotten");
        return departmentsDTO;
    }

    private Enterprise getEnterprise(Long id) throws EnterpriseNotFoundException {
        return enterpriseRepository.findById(id)
                .orElseThrow( () -> new EnterpriseNotFoundException("enterprise not found or have been deleted"));
    }
}
