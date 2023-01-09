package com.brody.enterprisemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MissionsDTO {
    private Long id;
    private String name;
    private Long departmentId;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    List<MissionDTO> missionDTOList;
}
