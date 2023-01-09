package com.brody.enterprisemanagement.repositories;

import com.brody.enterprisemanagement.entities.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Page<Mission> findByDepartmentIdOrderByNameDesc(Long departmentId, Pageable pageable);

    @Query("select m from Mission m where m.name like :kw")
    List<Mission> findByName(@Param("kw") String keyword);
    @Query("select m from Mission m where m.description like :kw")
    List<Mission> findByDescription(@Param("kw") String keyword);
}
