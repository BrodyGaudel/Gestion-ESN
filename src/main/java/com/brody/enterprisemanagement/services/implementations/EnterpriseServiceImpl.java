package com.brody.enterprisemanagement.services.implementations;

import com.brody.enterprisemanagement.dto.EnterpriseDTO;
import com.brody.enterprisemanagement.entities.Enterprise;
import com.brody.enterprisemanagement.exceptions.EnterpriseNotFoundException;
import com.brody.enterprisemanagement.mapping.Mappers;
import com.brody.enterprisemanagement.repositories.EnterpriseRepository;
import com.brody.enterprisemanagement.services.EnterpriseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EnterpriseServiceImpl implements EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;
    private final Mappers mappers;

    public EnterpriseServiceImpl(EnterpriseRepository enterpriseRepository, Mappers mappers) {
        this.enterpriseRepository = enterpriseRepository;
        this.mappers = mappers;
    }


    @Override
    public EnterpriseDTO save(EnterpriseDTO enterpriseDTO) {
        log.info("In save()");
        Enterprise enterprise = mappers.fromEnterpriseDTO(enterpriseDTO);
        if((enterprise != null) && (enterprise.getName() != null) && (enterprise.getRaisonSocial() != null)){
            Enterprise enterpriseSaved = enterpriseRepository.save(enterprise);
            log.info("enterprise saved");
            return mappers.fromEnterprise(enterpriseSaved);
        }
        else{
            log.info("enterprise not saved");
            return null;
        }
    }

    @Override
    public EnterpriseDTO update(EnterpriseDTO enterpriseDTO) throws EnterpriseNotFoundException {
        log.info("In update()");
        if(enterpriseDTO != null){
            Enterprise enterprise = findById(enterpriseDTO.getId());

            if(enterpriseDTO.getName() != null){
                enterprise.setName(enterpriseDTO.getName());
            }
            if(enterpriseDTO.getRaisonSocial() != null){
                enterprise.setRaisonSocial(enterpriseDTO.getRaisonSocial());
            }
            Enterprise enterpriseUpdated = enterpriseRepository.save(enterprise);
            log.info("enterprise updated");
            return mappers.fromEnterprise(enterpriseUpdated);
        }else {
            log.info("enterprise not updated");
            return null;
        }

    }

    @Override
    public EnterpriseDTO getById(Long id) throws EnterpriseNotFoundException {
        log.info("In get()");
        return mappers.fromEnterprise(findById(id));
    }

    @Override
    public List<EnterpriseDTO> getAll() {
        log.info("In getAll()");
        List<Enterprise> enterprises = enterpriseRepository.findAll();
        log.info("enterprises found");
        return mappers.fromListOfEnterprises(enterprises);
    }

    @Override
    public List<EnterpriseDTO> findByName(String keyword) {
        log.info("In find()");
        List<Enterprise> enterprises = enterpriseRepository.findByName(keyword);
        log.info("enterprise(s) found");
        return mappers.fromListOfEnterprises(enterprises);
    }

    @Override
    public void delete(Long id) {
        enterpriseRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        enterpriseRepository.deleteAll();
    }

    private Enterprise findById(Long id) throws EnterpriseNotFoundException {
        return enterpriseRepository.findById(id)
                .orElseThrow(()->new EnterpriseNotFoundException("enterprise not found"));
    }
}
