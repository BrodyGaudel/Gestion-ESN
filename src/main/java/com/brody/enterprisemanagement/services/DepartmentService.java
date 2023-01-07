package com.brody.enterprisemanagement.services;

import com.brody.enterprisemanagement.dto.DepartmentDTO;
import com.brody.enterprisemanagement.dto.DepartmentsDTO;
import com.brody.enterprisemanagement.exceptions.DepartmentNotFoundException;
import com.brody.enterprisemanagement.exceptions.EnterpriseNotFoundException;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO findById(Long id) throws DepartmentNotFoundException;
    List<DepartmentDTO> findByEnterpriseId(Long id) throws EnterpriseNotFoundException;
    List<DepartmentDTO> findAll();

    DepartmentDTO save(DepartmentDTO departmentDTO) throws EnterpriseNotFoundException;
    DepartmentDTO update(DepartmentDTO departmentDTO) throws DepartmentNotFoundException, EnterpriseNotFoundException;

    void deleteById(Long id);
    void deleteAll();

    void deleteAllByDepartmentId(Long departmentId) throws EnterpriseNotFoundException;

    DepartmentsDTO getDepartments(Long enterpriseId, int page, int size) throws EnterpriseNotFoundException;

}
