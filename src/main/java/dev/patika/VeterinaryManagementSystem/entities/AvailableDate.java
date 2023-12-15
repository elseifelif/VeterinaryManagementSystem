package dev.patika.VeterinaryManagementSystem.entities;

import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "available_date")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AvailableDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "available_date", nullable = false)
    private LocalDate availableDate;

    // Değerlendirme Formu #9 : Entity’ler arasındaki bağlantılar (@OneToMany, @ManyToOne, @ManyToMany vs.)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
}
