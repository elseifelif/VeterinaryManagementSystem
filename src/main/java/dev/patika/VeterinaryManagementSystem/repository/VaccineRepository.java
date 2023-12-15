package dev.patika.VeterinaryManagementSystem.repository;

import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.AppointmentDate;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepository extends JpaRepository <Vaccine,Long> {

    List<Vaccine>findByAnimalId(Long id);

    Optional<Vaccine> findById (Long id);

    Optional<Vaccine> findByCode(String code);

    List<Vaccine> findByAnimalId(Animal animal);

    List<Vaccine> findByProtectionStartDateBetween(LocalDate startDate, LocalDate endDate);

    Optional<Vaccine> findByAnimalAndName(Animal animal, String name);

}
