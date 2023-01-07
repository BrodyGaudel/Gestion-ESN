package com.brody.enterprisemanagement.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Embeddable
public class TimesheetPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idMission;

    private int idEmployee;

    @Temporal(TemporalType.DATE)
    private Date start;

    @Temporal(TemporalType.DATE)
    private Date end;

    public TimesheetPK() {
        super();
    }

    public TimesheetPK(int idMission, int idEmployee, Date start, Date end) {
        this.idMission = idMission;
        this.idEmployee = idEmployee;
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimesheetPK that = (TimesheetPK) o;
        return idMission == that.idMission && idEmployee == that.idEmployee && Objects.equals(start, that.start) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMission, idEmployee, start, end);
    }

    public int getIdMission() {
        return idMission;
    }

    public void setIdMission(int idMission) {
        this.idMission = idMission;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "TimesheetPK{" +
                "idMission=" + idMission +
                ", idEmployee=" + idEmployee +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
