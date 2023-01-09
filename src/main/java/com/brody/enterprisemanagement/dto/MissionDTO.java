package com.brody.enterprisemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MissionDTO {
    private Long id;
    private String name;
    private String description;
    private Long departmentId;
    private String emailBilling;
    private BigDecimal averageDailyRate;
}
