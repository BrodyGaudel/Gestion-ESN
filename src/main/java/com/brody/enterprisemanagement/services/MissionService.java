package com.brody.enterprisemanagement.services;

import com.brody.enterprisemanagement.dto.MissionDTO;
import com.brody.enterprisemanagement.dto.MissionsDTO;
import com.brody.enterprisemanagement.enums.MissionNotFoundException;
import com.brody.enterprisemanagement.exceptions.DepartmentNotFoundException;

import java.util.List;

public interface MissionService {

    MissionDTO save(MissionDTO missionDTO);
    MissionDTO update(MissionDTO missionDTO) throws MissionNotFoundException;
    MissionDTO addMissionToDepartment(Long missionId, Long departmentId) throws MissionNotFoundException, DepartmentNotFoundException;
    MissionDTO deleteMissionFromDepartment(Long missionId, Long departmentId) throws MissionNotFoundException, DepartmentNotFoundException;
    List<MissionDTO> findAll();
    List<MissionDTO> findMissionByDepartmentId(Long id) throws DepartmentNotFoundException;
    List<MissionDTO> findMissionByDepartmentIdAndByNameOrDescription(String keyword, Long id);
    MissionsDTO getMissionPageable(Long departmentId, int page, int size);
    MissionDTO findById(Long id) throws MissionNotFoundException;
    void deleteAll();
    void deleteAllByDepartmentId(Long id) throws DepartmentNotFoundException;
    void deleteById(Long id);
}
