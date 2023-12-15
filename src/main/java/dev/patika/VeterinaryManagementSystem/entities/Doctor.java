package dev.patika.VeterinaryManagementSystem.entities;

import dev.patika.VeterinaryManagementSystem.dto.response.AvailableDateResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "doctor")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    // Değerlendirme Formu #9 : Entity’ler arasındaki bağlantılar (@OneToMany, @ManyToOne, @ManyToMany vs.)
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<AvailableDate> availableDates;

    // Değerlendirme Formu #9 : Entity’ler arasındaki bağlantılar (@OneToMany, @ManyToOne, @ManyToMany vs.)
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<AppointmentDate> appointmentDates;

}