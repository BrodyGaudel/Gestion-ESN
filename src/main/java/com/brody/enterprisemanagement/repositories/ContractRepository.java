package com.brody.enterprisemanagement.repositories;

import com.brody.enterprisemanagement.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
