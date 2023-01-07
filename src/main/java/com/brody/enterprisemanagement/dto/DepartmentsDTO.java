package com.brody.enterprisemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentsDTO {
    private Long enterpriseId;
    private String name;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private List<DepartmentDTO> departmentDTOList;
}
