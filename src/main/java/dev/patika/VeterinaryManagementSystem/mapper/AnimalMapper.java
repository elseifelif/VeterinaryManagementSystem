package dev.patika.VeterinaryManagementSystem.mapper;

import dev.patika.VeterinaryManagementSystem.dto.request.AnimalRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.AnimalResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper
public interface AnimalMapper {

    Animal asEntity(AnimalRequest animalRequest);

    AnimalResponse asOutput(Animal animal);

    List<AnimalResponse> asOutput(List<Animal> animal);

    void update(@MappingTarget Animal entity, AnimalRequest request);

}
