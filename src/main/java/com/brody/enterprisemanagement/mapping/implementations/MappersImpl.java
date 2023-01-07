package com.brody.enterprisemanagement.mapping.implementations;

import com.brody.enterprisemanagement.dto.EnterpriseDTO;
import com.brody.enterprisemanagement.entities.Enterprise;
import com.brody.enterprisemanagement.mapping.Mappers;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MappersImpl implements Mappers {
    @Override
    public EnterpriseDTO fromEnterprise(Enterprise enterprise) {
        try{
            EnterpriseDTO enterpriseDTO = new EnterpriseDTO();
            enterpriseDTO.setId(enterprise.getId());
            enterpriseDTO.setName(enterprise.getName());
            enterpriseDTO.setRaisonSocial(enterprise.getRaisonSocial());
            return enterpriseDTO;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Enterprise fromEnterpriseDTO(EnterpriseDTO enterpriseDTO) {
        try{
            Enterprise enterprise = new Enterprise();
            enterprise.setId(enterpriseDTO.getId());
            enterprise.setName(enterpriseDTO.getName());
            enterprise.setRaisonSocial(enterpriseDTO.getRaisonSocial());
            return enterprise;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<EnterpriseDTO> fromListOfEnterprises(List<Enterprise> enterprises) {
        try{
            return enterprises.stream().map(this::fromEnterprise).toList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }
}