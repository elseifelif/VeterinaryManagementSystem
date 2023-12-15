package dev.patika.VeterinaryManagementSystem.repository;

import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.AppointmentDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentDateRepository extends JpaRepository<AppointmentDate,Long> {

    List<AppointmentDate> findByDoctorId(Long doctorId);
    List<AppointmentDate> findByAppointmentDateBetweenAndAnimalId(LocalDateTime startDate, LocalDateTime endDate, Long animalId);

    List<AppointmentDate> findByAppointmentDateBetweenAndDoctorId(LocalDateTime startDate, LocalDateTime endDate, Long doctorId);

    @Modifying
    @Query("UPDATE AppointmentDate SET appointmentDate = :newDate WHERE id = :id")
    void updateAppointmentDate(@Param("id") Long id, @Param("newDate") LocalDateTime newDate);
}
