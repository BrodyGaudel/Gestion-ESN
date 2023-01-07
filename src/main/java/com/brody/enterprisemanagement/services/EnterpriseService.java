package com.brody.enterprisemanagement.services;

import com.brody.enterprisemanagement.dto.EnterpriseDTO;
import com.brody.enterprisemanagement.exceptions.EnterpriseNotFoundException;

import java.util.List;

public interface EnterpriseService {

    /**
     * Save an enterprise
     * @param enterpriseDTO type EnterpriseDTO
     * @return EnterpriseDTO
     */
    EnterpriseDTO save(EnterpriseDTO enterpriseDTO);

    /**
     * update an enterprise
     * @param enterpriseDTO EnterpriseDTO
     * @return EnterpriseDTO
     * @throws EnterpriseNotFoundException if EnterpriseDTO does not found
     */
    EnterpriseDTO update(EnterpriseDTO enterpriseDTO) throws EnterpriseNotFoundException;

    /**
     * get an enterprise by its id
     * @param id Long
     * @return EnterpriseDTO
     * @throws EnterpriseNotFoundException if EnterpriseDTO does not found
     */
    EnterpriseDTO getById(Long id) throws EnterpriseNotFoundException;

    /**
     * get all enterprise
      * @return List<EnterpriseDTO>
     */
    List<EnterpriseDTO> getAll();

    /**
     * search enterprises by name
     * @param keyword String
     * @return List<EnterpriseDTO>
     */
    List<EnterpriseDTO> findByName(String keyword);

    /**
     * delete an enterprise by its id
     * @param id Long
     */
    void delete(Long id);

    /**
     * delete all enterprises
     */
    void deleteAll();
}
