package com.brody.enterprisemanagement.restcontroller;

import com.brody.enterprisemanagement.dto.EmployeeDTO;
import com.brody.enterprisemanagement.entities.Contract;
import com.brody.enterprisemanagement.enums.Role;
import com.brody.enterprisemanagement.exceptions.DepartmentNotFoundException;
import com.brody.enterprisemanagement.exceptions.EmployeeNotFoundException;
import com.brody.enterprisemanagement.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin(origins = "*")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/findById/{id}")
    @ResponseBody
    public EmployeeDTO findById(@PathVariable(name="id") Long id) throws EmployeeNotFoundException{
        return employeeService.findById(id);
    }

    @GetMapping("/search/{keyword}")
    @ResponseBody
    public List<EmployeeDTO> findByFirstnameOrName(@PathVariable(name="keyword") String keyword){
        return employeeService.findByFirstnameOrName("%"+keyword+"%");
    }

    @GetMapping("/findAll")
    @ResponseBody
    public List<EmployeeDTO> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/findByDepartmentId/{id}")
    @ResponseBody
    public List<EmployeeDTO> findByDepartmentId(@PathVariable(name="id") Long id){
        return employeeService.findByDepartmentId(id);
    }

    @PostMapping("/save")
    @ResponseBody
    public EmployeeDTO save(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.save(employeeDTO);
    }

    @PutMapping("/update")
    @ResponseBody
    public EmployeeDTO update(@RequestBody EmployeeDTO employeeDTO) throws EmployeeNotFoundException{
        return employeeService.update(employeeDTO);
    }

    @DeleteMapping("deleteById/{id}")
    public void deleteById(@PathVariable(name="id") Long id){
        employeeService.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        employeeService.deleteAll();
    }

    @GetMapping("/json")
    @ResponseBody
    public EmployeeDTO json(){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Contract contract = new Contract();
        contract.setEnd(new Date());
        contract.setStart(new Date());
        employeeDTO.setContract(contract);
        employeeDTO.setRole(Role.ENGINEER);
        employeeDTO.setIsActif(true);
        return employeeDTO;
    }

    @GetMapping("/affect/{idEmployee}/{idDepartment}")
    @ResponseBody
    public Boolean addEmployeeToDepartment(@PathVariable(name="idEmployee") Long idEmployee,
                                           @PathVariable(name="idDepartment") Long idDepartment) throws EmployeeNotFoundException, DepartmentNotFoundException {
        return employeeService.addEmployeeToDepartment(idEmployee, idDepartment);
    }

    @GetMapping("/unaffected/{idEmployee}/{idDepartment}")
    @ResponseBody
    public Boolean deleteEmployeeFromDepartment(@PathVariable(name="idEmployee") Long idEmployee,
                                                @PathVariable(name="idDepartment") Long idDepartment) throws EmployeeNotFoundException, DepartmentNotFoundException {
        return employeeService.deleteEmployeeFromDepartment(idEmployee, idDepartment);
    }

}
