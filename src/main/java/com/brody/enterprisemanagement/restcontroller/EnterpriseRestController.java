package com.brody.enterprisemanagement.restcontroller;

import com.brody.enterprisemanagement.dto.EnterpriseDTO;
import com.brody.enterprisemanagement.exceptions.EnterpriseNotFoundException;
import com.brody.enterprisemanagement.services.EnterpriseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enterprise")
@CrossOrigin(origins = "*")
public class EnterpriseRestController {

    private final EnterpriseService enterpriseService;

    public EnterpriseRestController(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    @PostMapping("/save")
    @ResponseBody
    public EnterpriseDTO save(@RequestBody EnterpriseDTO enterpriseDTO){
        return enterpriseService.save(enterpriseDTO);
    }

    @PutMapping("/update")
    @ResponseBody
    public EnterpriseDTO update(@RequestBody EnterpriseDTO enterpriseDTO) throws EnterpriseNotFoundException{
        return enterpriseService.update(enterpriseDTO);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public EnterpriseDTO getById(@PathVariable(name="id") Long id) throws EnterpriseNotFoundException{
        return enterpriseService.getById(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<EnterpriseDTO> getAll(){
        return enterpriseService.getAll();
    }

    @GetMapping("/search/{name}")
    @ResponseBody
    public List<EnterpriseDTO> findByName(@PathVariable(name="name") String name){
        return enterpriseService.findByName("%"+name+"%");
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name="id") Long id){
        enterpriseService.delete(id);
    }

    @DeleteMapping("/clear")
    public void deleteAll(){
        enterpriseService.deleteAll();
    }
}
