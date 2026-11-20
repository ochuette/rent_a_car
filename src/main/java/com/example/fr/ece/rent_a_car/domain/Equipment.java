package com.example.fr.ece.rent_a_car.domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "equipment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipment;

    @Column(nullable = false, length = 100)
    private String libel;

    @ManyToMany(mappedBy = "equipments", fetch = FetchType.LAZY)
    private Set<Vehicule> vehicules = new HashSet<>();
}
