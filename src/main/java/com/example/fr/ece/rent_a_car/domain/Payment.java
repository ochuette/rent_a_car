package com.example.fr.ece.rent_a_car.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;

    @Column(precision = 12, scale = 12)

    private BigDecimal montant;

    private LocalDate datePayment;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ModePayment modePayment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contract contract;
}
