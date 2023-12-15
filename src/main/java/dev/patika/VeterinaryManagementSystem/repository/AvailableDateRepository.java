package dev.patika.VeterinaryManagementSystem.repository;

import dev.patika.VeterinaryManagementSystem.entities.AppointmentDate;
import dev.patika.VeterinaryManagementSystem.entities.AvailableDate;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate,Long> {

List<AvailableDate> findByDoctor (Doctor doctor);

List<AppointmentDate> findByDoctorId(Long doctorId);

List<AvailableDate> findByDoctorIdAndAvailableDate(Long doctorId, LocalDate availableDate);

}
