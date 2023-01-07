package com.brody.enterprisemanagement.mapping;

import com.brody.enterprisemanagement.dto.EnterpriseDTO;
import com.brody.enterprisemanagement.entities.Enterprise;

import java.util.List;

public interface Mappers {
    EnterpriseDTO fromEnterprise(Enterprise enterprise);
    Enterprise fromEnterpriseDTO(EnterpriseDTO enterpriseDTO);
    List<EnterpriseDTO> fromListOfEnterprises(List<Enterprise> enterprises);

}
