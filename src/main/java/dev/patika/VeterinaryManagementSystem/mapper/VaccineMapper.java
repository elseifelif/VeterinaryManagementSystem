package dev.patika.VeterinaryManagementSystem.mapper;

import dev.patika.VeterinaryManagementSystem.dto.request.VaccineRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.VaccineResponse;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface VaccineMapper {

    Vaccine asEntity(VaccineRequest vaccineRequest);

    VaccineResponse asOutput(Vaccine vaccine);

    List<VaccineResponse> asOutput(List<Vaccine> vaccine);

    void update(@MappingTarget Vaccine entity, VaccineRequest request);

}
