package com.brody.enterprisemanagement.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "timesheet")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Timesheet implements Serializable {

    @EmbeddedId
    private TimesheetPK timesheetPK;

    @ManyToOne
    @JoinColumn(name = "idMission", referencedColumnName = "id", insertable=false, updatable=false)
    private Mission mission;

    @ManyToOne
    @JoinColumn(name = "idEmployee", referencedColumnName = "id", insertable=false, updatable=false)
    private Employee employee;

    private Boolean isValide;


}
