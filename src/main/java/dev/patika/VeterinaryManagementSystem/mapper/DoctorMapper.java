package dev.patika.VeterinaryManagementSystem.mapper;

import dev.patika.VeterinaryManagementSystem.dto.request.DoctorRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.DoctorResponse;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface DoctorMapper {

    Doctor asEntity(DoctorRequest doctorRequest);

    DoctorResponse asOutput(Doctor doctor);

    List<DoctorResponse> asOutput(List<Doctor> doctor);

    void update(@MappingTarget Doctor entity, DoctorRequest request);


}
