package com.brody.enterprisemanagement.repositories;

import com.brody.enterprisemanagement.entities.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
}
