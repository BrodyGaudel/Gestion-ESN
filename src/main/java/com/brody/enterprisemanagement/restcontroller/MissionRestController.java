package com.brody.enterprisemanagement.restcontroller;

import com.brody.enterprisemanagement.dto.MissionDTO;
import com.brody.enterprisemanagement.dto.MissionsDTO;
import com.brody.enterprisemanagement.enums.MissionNotFoundException;
import com.brody.enterprisemanagement.exceptions.DepartmentNotFoundException;
import com.brody.enterprisemanagement.services.MissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mission")
@CrossOrigin(origins = "*")
public class MissionRestController {

    private final MissionService missionService;

    public MissionRestController(MissionService missionService) {
        this.missionService = missionService;
    }

    @PostMapping("/save")
    @ResponseBody
    public MissionDTO save(@RequestBody MissionDTO missionDTO){
        return missionService.save(missionDTO);
    }

    @PutMapping("/update")
    @ResponseBody
    public MissionDTO update(@RequestBody MissionDTO missionDTO) throws MissionNotFoundException{
        return missionService.update(missionDTO);
    }

    @GetMapping("/affect/{missionId}/{departmentId}")
    @ResponseBody
    public MissionDTO addMissionToDepartment(
            @PathVariable(name="missionId") Long missionId, @PathVariable(name="departmentId") Long departmentId) throws MissionNotFoundException, DepartmentNotFoundException{
        return missionService.addMissionToDepartment(missionId, departmentId);

    }

    @GetMapping("/unaffected/{missionId}/{departmentId}")
    @ResponseBody
    public MissionDTO deleteMissionFromDepartment(
            @PathVariable(name="missionId") Long missionId, @PathVariable(name="departmentId") Long departmentId) throws MissionNotFoundException, DepartmentNotFoundException{
        return missionService.deleteMissionFromDepartment(missionId,departmentId);
    }

    @GetMapping("/findAll")
    @ResponseBody
    public List<MissionDTO> findAll(){
        return missionService.findAll();
    }

    @GetMapping("/findByDepartmentId/{id}")
    @ResponseBody
    public List<MissionDTO> findMissionByDepartmentId(@PathVariable(name="id") Long id) throws DepartmentNotFoundException{
        return missionService.findMissionByDepartmentId(id);
    }

    @GetMapping("/search/{keyword}/{id}")
    @ResponseBody
    public List<MissionDTO> findMissionByDepartmentIdAndByNameOrDescription(
            @PathVariable(name="keyword") String keyword, @PathVariable(name="id") Long id){
        return missionService.findMissionByDepartmentIdAndByNameOrDescription("%"+keyword+"%",id);
    }

    @GetMapping("/{departmentId}/page")
    @ResponseBody
    public MissionsDTO getMissionPageable(@PathVariable Long departmentId,
                                          @RequestParam(name ="page", defaultValue = "0") int page,
                                          @RequestParam(name ="size", defaultValue = "5") int size){
        return missionService.getMissionPageable(departmentId,page,size);
    }

    @GetMapping("/findById/{id}")
    @ResponseBody
    public MissionDTO findById(@PathVariable(name="id") Long id) throws MissionNotFoundException{
        return missionService.findById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        missionService.deleteAll();
    }

    @DeleteMapping("/deleteAllByDepartmentId/{id}")
    public void deleteAllByDepartmentId(@PathVariable(name="id") Long id) throws DepartmentNotFoundException{
        missionService.deleteAllByDepartmentId(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable(name="id") Long id){
        missionService.deleteById(id);
    }
}
