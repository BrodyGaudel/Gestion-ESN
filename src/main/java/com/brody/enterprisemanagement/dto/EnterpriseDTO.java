package com.brody.enterprisemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EnterpriseDTO {
    private Long id;
    private String name;
    private String raisonSocial;
}
