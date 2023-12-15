package dev.patika.VeterinaryManagementSystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "appointment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "appointment_date", nullable = false)
    private LocalDateTime appointmentDate;

    // Değerlendirme Formu #9 : Entity’ler arasındaki bağlantılar (@OneToMany, @ManyToOne, @ManyToMany vs.)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    // Değerlendirme Formu #9 : Entity’ler arasındaki bağlantılar (@OneToMany, @ManyToOne, @ManyToMany vs.)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id")
    private Animal animal;


}
