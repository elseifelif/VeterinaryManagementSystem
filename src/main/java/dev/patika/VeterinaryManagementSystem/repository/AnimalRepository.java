package dev.patika.VeterinaryManagementSystem.repository;

import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal,Long> {

    Optional<Animal> findByName(String name);

    List<Animal> findByCustomer (Customer customer);

    List<Animal> findByNameContainingIgnoreCase(String name);

    Optional<Animal> findById (Long id);


}
