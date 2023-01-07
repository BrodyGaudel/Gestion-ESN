package com.brody.enterprisemanagement.dto;

import com.brody.enterprisemanagement.entities.Contract;
import com.brody.enterprisemanagement.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeeDTO {
    private Long id;
    private String firstname;
    private String name;
    private String email;
    private Role role;
    private Boolean isActif;
    private Contract contract;
}
