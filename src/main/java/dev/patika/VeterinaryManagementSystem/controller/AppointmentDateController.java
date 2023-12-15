package dev.patika.VeterinaryManagementSystem.controller;

import dev.patika.VeterinaryManagementSystem.dto.request.AppointmentDateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.AppointmentDateResponse;
import dev.patika.VeterinaryManagementSystem.service.AppointmentDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointmentDate")
public class AppointmentDateController {

    private final AppointmentDateService appointmentDateService;

    @Autowired
    public AppointmentDateController(AppointmentDateService appointmentDateService) {
        this.appointmentDateService = appointmentDateService;
    }

    // Değerlendirme Formu #14 : randevu tarihi kaydetmek için gereken controller ve
    // service katmanlarının oluşturulması
    // Randevu tarihi kaydederken tarih ve saat formatı aşağıdaki örnekteki gibi olmalıdır.
    // "appointmentDate": "2023-01-01 10:00"

    // Değerlendirme Formu #22 : randevu tarihi kaydederken doktorun o saatte başka bir
    // randevusu varsa veya günü müsait değilse yeni randevu kaydedilemez.

    @PostMapping("/create-with-doctor-and-animal/{doctorId}/{animalId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDateResponse saveWithDoctorAndAnimal(
            @PathVariable Long doctorId,
            @PathVariable Long animalId,
            @RequestBody AppointmentDateRequest appointmentDateRequest) {
        return appointmentDateService.createWithDoctorAndAnimal(doctorId, animalId, appointmentDateRequest);
    }

    // Değerlendirme Formu #24 : randevuların tarih aralığına ve doktora göre filtrelenmesi için
    // gerekli controller ve service katmanlarının oluşturulması
    @GetMapping("/filter-by-date-range-and-doctor")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDateResponse> getAppointmentsByDateRangeAndDoctor(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam Long doctorId) {
        return appointmentDateService.getAppointmentsByDateRangeAndDoctorId(startDate, endDate, doctorId);
    }

    // Değerlendirme Formu #23 : randevuların tarih aralığına ve hayvana göre filtrelenmesi için
    // gerekli controller ve service katmanlarının oluşturulması
    @GetMapping("/filter-by-date-range-and-animal")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDateResponse> getAppointmentsByDateRangeAndAnimal(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam Long animalId) {
        return appointmentDateService.getAppointmentsByDateRangeAndAnimalId(startDate, endDate, animalId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDateResponse> findAll() {
        return appointmentDateService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentDateResponse getById(Long id) {
        return appointmentDateService.getById(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentDateResponse update(@PathVariable Long id, @RequestBody AppointmentDateRequest request) {
        return appointmentDateService.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        appointmentDateService.deleteById(id);
    }

}
