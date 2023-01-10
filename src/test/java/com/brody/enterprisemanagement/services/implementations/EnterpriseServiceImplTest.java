package com.brody.enterprisemanagement.services.implementations;

import com.brody.enterprisemanagement.dto.EnterpriseDTO;
import com.brody.enterprisemanagement.entities.Enterprise;
import com.brody.enterprisemanagement.exceptions.EnterpriseNotFoundException;
import com.brody.enterprisemanagement.repositories.EnterpriseRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EnterpriseServiceImplTest {

    @Autowired
    private EnterpriseServiceImpl service;
    @Autowired
    private EnterpriseRepository repository;

    @Test
    void save() {
        EnterpriseDTO enterprise = service.save(new EnterpriseDTO(null, "LifeSoft", "SA"));
        assertNotNull(enterprise);
    }

    @Test
    void update() throws EnterpriseNotFoundException {
        Enterprise e = repository.save(new Enterprise(null, "LifeSoft", "SA",null));
        EnterpriseDTO enterprise = service.update(new EnterpriseDTO(e.getId(), "MicroSoft", "SA"));
        assertEquals(e.getId(),enterprise.getId());
    }

    @Test
    void getById() throws EnterpriseNotFoundException {
        Enterprise e = repository.save(new Enterprise(null, "LifeSoft", "SA",null));
        EnterpriseDTO enterprise = service.getById(e.getId());
        assertNotNull(enterprise);
        assertEquals(e.getId(), enterprise.getId());
    }

    @Test
    void getAll() {
        List<EnterpriseDTO> enterpriseDTOList = service.getAll();
        assertNotNull(enterpriseDTOList);
    }

    @Test
    void findByName() {
        Enterprise e = repository.save(new Enterprise(null, "LifeSoft", "SA",null));
        List<EnterpriseDTO> enterpriseDTOList = service.findByName("%"+e.getName()+"%");
        assertNotNull(enterpriseDTOList);
    }

    @Test
    void delete() {
        Enterprise e1 = repository.save(new Enterprise(null, "LifeSoft", "SA",null));
        service.delete(e1.getId());
        Enterprise e2 = repository.findById(e1.getId()).orElse(null);
        assertNull(e2);
    }
}