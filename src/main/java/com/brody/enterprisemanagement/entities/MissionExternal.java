package com.brody.enterprisemanagement.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MissionExternal extends Mission {

    private static final long serialVersionUID = 1L;

    private String emailBilling;
    private BigDecimal averageDailyRate;
}
