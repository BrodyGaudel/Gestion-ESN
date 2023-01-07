package com.brody.enterprisemanagement.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "enterprise")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Enterprise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "raison_social", nullable = false)
    private String raisonSocial;

    @JsonManagedReference
    @OneToMany(mappedBy = "enterprise")
    private List<Department> departments;
}
