package com.brody.enterprisemanagement.services.implementations;

import com.brody.enterprisemanagement.dto.MissionDTO;
import com.brody.enterprisemanagement.dto.MissionsDTO;
import com.brody.enterprisemanagement.entities.Department;
import com.brody.enterprisemanagement.entities.Mission;
import com.brody.enterprisemanagement.entities.MissionExternal;
import com.brody.enterprisemanagement.enums.MissionNotFoundException;
import com.brody.enterprisemanagement.exceptions.DepartmentNotFoundException;
import com.brody.enterprisemanagement.mapping.Mappers;
import com.brody.enterprisemanagement.repositories.DepartmentRepository;
import com.brody.enterprisemanagement.repositories.MissionExternalRepository;
import com.brody.enterprisemanagement.repositories.MissionRepository;
import com.brody.enterprisemanagement.services.MissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private final DepartmentRepository departmentRepository;
    private final Mappers mappers;
    private final MissionExternalRepository missionExternalRepository;

    public MissionServiceImpl(MissionRepository missionRepository, DepartmentRepository departmentRepository, Mappers mappers, MissionExternalRepository missionExternalRepository) {
        this.missionRepository = missionRepository;
        this.departmentRepository = departmentRepository;
        this.mappers = mappers;
        this.missionExternalRepository = missionExternalRepository;
    }


    @Override
    public MissionDTO save(MissionDTO missionDTO) {

        Department department = departmentRepository.findById(missionDTO.getDepartmentId()).orElse(null);

        if(department == null){
            return null;
        }else {
            if((missionDTO.getAverageDailyRate() != null )&&(missionDTO.getEmailBilling() != null)){
                MissionExternal missionExternalSaved;

                MissionExternal missionExternal = new MissionExternal();
                missionExternal.setId(missionDTO.getId());
                missionExternal.setName(missionDTO.getName());
                missionExternal.setDescription(missionDTO.getDescription());
                missionExternal.setEmailBilling(missionDTO.getEmailBilling());
                missionExternal.setAverageDailyRate(missionDTO.getAverageDailyRate());
                missionExternal.setDepartment(department);
                missionExternalSaved = missionExternalRepository.save(missionExternal);
                return mappers.fromMission(missionExternalSaved);
            }else {

                Mission missionSaved;
                Mission mission = mappers.fromMissionDTO(missionDTO);
                mission.setDepartment(department);
                missionSaved = missionRepository.save(mission);
                return mappers.fromMission(missionSaved);
            }
        }
    }

    @Override
    public MissionDTO update(MissionDTO missionDTO) throws MissionNotFoundException {
        Mission mission = missionRepository.findById(missionDTO.getId())
                .orElseThrow( () -> new MissionNotFoundException("Mission Not Found"));
        Mission updated;
       if(mission instanceof MissionExternal missionExternal){
           missionExternal.setEmailBilling(missionDTO.getEmailBilling());
           missionExternal.setAverageDailyRate(missionDTO.getAverageDailyRate());
           missionExternal.setName(missionDTO.getName());
           missionExternal.setDescription(missionDTO.getDescription());
           missionExternal.setId(mission.getId());
           missionExternal.setDepartment(mission.getDepartment());
           updated = missionRepository.save(missionExternal);
       }else {
           mission.setDescription(missionDTO.getDescription());
           mission.setName(missionDTO.getName());
           updated = missionRepository.save(mission);
       }
        return mappers.fromMission(updated);
    }

    @Override
    public MissionDTO addMissionToDepartment(Long missionId, Long departmentId) throws MissionNotFoundException, DepartmentNotFoundException {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow( () -> new MissionNotFoundException("Mission DOES NOT EXIST"));
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow( () -> new DepartmentNotFoundException("Mission DOES NOT EXIST"));

        List<Mission> missions = department.getMissions();
        missions.add(mission);
        department.setMissions(missions);
        mission.setDepartment(department);
        departmentRepository.save(department);
        missionRepository.save(mission);

        return mappers.fromMission(mission);

    }

    @Override
    public MissionDTO deleteMissionFromDepartment(Long missionId, Long departmentId) throws MissionNotFoundException, DepartmentNotFoundException {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow( () -> new MissionNotFoundException("Mission(s) DOES NOT EXIST"));
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow( () -> new DepartmentNotFoundException("Mission DOESN'T EXIST"));

        List<Mission> missions = department.getMissions();
        missions.remove(mission);
        department.setMissions(missions);
        mission.setDepartment(null);
        departmentRepository.save(department);
        missionRepository.save(mission);
        return mappers.fromMission(mission);
    }

    @Override
    public List<MissionDTO> findAll() {
        List<Mission> missions = missionRepository.findAll();
        return mappers.fromListOfMissions(missions);
    }

    @Override
    public List<MissionDTO> findMissionByDepartmentId(Long id) throws DepartmentNotFoundException {
        Department department = departmentRepository.findById(id)
                .orElseThrow( () -> new DepartmentNotFoundException("Mission DOESN'T EXIST OR HAVE BEEN DELETED"));

        List<Mission> missions = missionRepository.findAll()
                .stream()
                .filter(mission -> mission.getDepartment().getId().equals(department.getId()))
                .toList();

        return mappers.fromListOfMissions(missions);
    }

    @Override
    public List<MissionDTO> findMissionByDepartmentIdAndByNameOrDescription(String keyword, Long id) {
        List<Mission> missions1 = missionRepository.findByName(keyword);
        List<Mission> missions2 = missionRepository.findByDescription(keyword);
        List<Mission> missions = new ArrayList<>();
        missions.addAll(missions1);
        missions.addAll(missions2);
        List<Mission> missionList = missions.stream().filter(m ->m.getDepartment().getId().equals(id)).toList();
        return mappers.fromListOfMissions(missionList);
    }

    @Override
    public MissionsDTO getMissionPageable(Long departmentId, int page, int size) {
        Department department = departmentRepository.findById(departmentId).orElse(null);

        if(department == null){
            return null;
        }else{
            Page<Mission> missionPage = missionRepository
                    .findByDepartmentIdOrderByNameDesc(departmentId, PageRequest.of(page, size));
            List<MissionDTO> missions = mappers.fromListOfMissions(missionPage.getContent());
            MissionsDTO missionsDTO = new MissionsDTO();
            missionsDTO.setMissionDTOList(missions);
            missionsDTO.setName(department.getName());
            missionsDTO.setId(departmentId);
            missionsDTO.setDepartmentId(departmentId);
            missionsDTO.setPageSize(size);
            missionsDTO.setCurrentPage(page);
            missionsDTO.setTotalPages(missionPage.getTotalPages());

            return missionsDTO;
        }
    }

    @Override
    public MissionDTO findById(Long id) throws MissionNotFoundException {
        Mission mission = missionRepository.findById(id)
                .orElseThrow( () -> new MissionNotFoundException("mission(s) doesn't exist"));
        return mappers.fromMission(mission);
    }

    @Override
    public void deleteAll() {
        missionRepository.deleteAll();
    }

    @Override
    public void deleteAllByDepartmentId(Long id) throws DepartmentNotFoundException {
        Department department = departmentRepository.findById(id)
                .orElseThrow( () -> new DepartmentNotFoundException("Mission DOESN'T EXIST OR HAVE BEEN DELETED"));
        List<Mission> missions = missionRepository.findAll()
                .stream()
                .filter(mission -> mission.getDepartment().getId().equals(department.getId()))
                .toList();
        for(Mission m: missions){
            missionRepository.deleteById(m.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        missionRepository.deleteById(id);
    }

}
