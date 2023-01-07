package com.brody.enterprisemanagement.restcontroller;


import com.brody.enterprisemanagement.dto.DepartmentDTO;
import com.brody.enterprisemanagement.dto.DepartmentsDTO;
import com.brody.enterprisemanagement.exceptions.DepartmentNotFoundException;
import com.brody.enterprisemanagement.exceptions.EnterpriseNotFoundException;
import com.brody.enterprisemanagement.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
@CrossOrigin(origins = "*")
public class DepartmentRestController {

    private final DepartmentService departmentService;

    public DepartmentRestController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/findById/{id}")
    @ResponseBody
    public DepartmentDTO findById(@PathVariable(name="id") Long id) throws DepartmentNotFoundException{
        return departmentService.findById(id);
    }

    @GetMapping("/findByEnterpriseId/{id}")
    @ResponseBody
    public List<DepartmentDTO> findByEnterpriseId(@PathVariable(name="id") Long id) throws EnterpriseNotFoundException{
        return departmentService.findByEnterpriseId(id);
    }

    @GetMapping("/findAll")
    @ResponseBody
    public List<DepartmentDTO> findAll(){
        return departmentService.findAll();
    }

    @PostMapping("/save")
    @ResponseBody
    public DepartmentDTO save(@RequestBody DepartmentDTO departmentDTO) throws EnterpriseNotFoundException{
        return departmentService.save(departmentDTO);
    }

    @PutMapping("/update")
    @ResponseBody
    public DepartmentDTO update(@RequestBody DepartmentDTO departmentDTO) throws DepartmentNotFoundException, EnterpriseNotFoundException{
        return departmentService.update(departmentDTO);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable(name="id") Long id){
        departmentService.deleteById(id);
    }

    @DeleteMapping("deleteAll")
    public void deleteAll(){
        departmentService.deleteAll();
    }

    @DeleteMapping("/deleteAllByDepartmentId/{id}")
    public void deleteAllByDepartmentId(@PathVariable(name="id") Long id) throws EnterpriseNotFoundException{
        departmentService.deleteAllByDepartmentId(id);
    }

    @GetMapping("/{enterpriseId}/page")
    @ResponseBody
    DepartmentsDTO getDepartments(@PathVariable Long enterpriseId,
                                  @RequestParam(name ="page", defaultValue = "0") int page,
                                  @RequestParam(name ="size", defaultValue = "5") int size) throws EnterpriseNotFoundException{
        return departmentService.getDepartments(enterpriseId,page,size);
    }
}
