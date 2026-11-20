package com.example.fr.ece.rent_a_car.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contract")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContract;

    private LocalDate dateSignature;

    @Column(precision = 12, scale = 2)

    private BigDecimal montantTotal;

    private Boolean valide;

    @OneToMany(mappedBy = "contract",  cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Payment> payments = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    private Reservation reservation;
}
