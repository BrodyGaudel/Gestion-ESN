package com.brody.enterprisemanagement.entities;

import com.brody.enterprisemanagement.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "contract")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "start", nullable = false)
    private Date start;

    @Column(name = "end", nullable = false)
    private Date end;

    @Transient
    @OneToOne(mappedBy = "contract")
    private Employee employee;
}
