package com.brody.enterprisemanagement.repositories;

import com.brody.enterprisemanagement.entities.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
