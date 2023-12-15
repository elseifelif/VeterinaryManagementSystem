package dev.patika.VeterinaryManagementSystem.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentDateRequest {


    // AppointmentDate eklenirken tarih formatı örnek olarak aşağıdaki gibi olmalıdır.
    // "appointmentDate": "2023-01-01 10:00"
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "UTC")
    private LocalDateTime appointmentDate;

}
