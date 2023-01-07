package com.brody.enterprisemanagement.repositories;

import com.brody.enterprisemanagement.entities.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {

    @Query( value = "SELECT * FROM enterprise WHERE  raisonSocial = ?1", nativeQuery = true)
    List<Enterprise> findByRaisonSocial(String raisonSocial);

    @Query("select e from Enterprise e where e.name like :kw")
    List<Enterprise> findByName(@Param("kw") String keyword);
}
