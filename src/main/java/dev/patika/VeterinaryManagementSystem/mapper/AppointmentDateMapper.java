package dev.patika.VeterinaryManagementSystem.mapper;

import dev.patika.VeterinaryManagementSystem.dto.request.AppointmentDateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.AppointmentDateResponse;
import dev.patika.VeterinaryManagementSystem.entities.AppointmentDate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper
public interface AppointmentDateMapper {

    AppointmentDate asEntity(AppointmentDateRequest appointmentDateRequest);

    AppointmentDateResponse asOutput(AppointmentDate appointmentDate);

    List<AppointmentDateResponse> asOutput(List<AppointmentDate> appointmentDate);

    void update(@MappingTarget AppointmentDate entity, AppointmentDateRequest appointmentDateRequest);

}
