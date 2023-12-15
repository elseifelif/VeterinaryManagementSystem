package dev.patika.VeterinaryManagementSystem.service;

import dev.patika.VeterinaryManagementSystem.dto.request.AnimalRequest;
import dev.patika.VeterinaryManagementSystem.dto.request.AppointmentDateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.AnimalResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.AppointmentDateResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.AppointmentDate;
import dev.patika.VeterinaryManagementSystem.entities.AvailableDate;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import dev.patika.VeterinaryManagementSystem.mapper.AppointmentDateMapper;
import dev.patika.VeterinaryManagementSystem.repository.AnimalRepository;
import dev.patika.VeterinaryManagementSystem.repository.AppointmentDateRepository;
import dev.patika.VeterinaryManagementSystem.repository.AvailableDateRepository;
import dev.patika.VeterinaryManagementSystem.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AppointmentDateService {

    private final AppointmentDateRepository appointmentDateRepository;
    private final AppointmentDateMapper appointmentDateMapper;
    private final DoctorRepository doctorRepository;
    private final AnimalRepository animalRepository;
    private final AvailableDateRepository availableDateRepository;

    public List<AppointmentDateResponse> findAll() {
        return appointmentDateMapper.asOutput(appointmentDateRepository.findAll());
    }

    public AppointmentDateResponse getById(Long id) {
        return appointmentDateMapper.asOutput(appointmentDateRepository.findById(id).orElseThrow(()
                -> new RuntimeException(id + "id li Randevu Bulunamadı !!!")));
    }

    // Değerlendirme Formu #14 : randevu tarihi kaydetmek için gereken controller ve
    // service katmanlarının oluşturulması
    // Randevu tarihi kaydederken tarih ve saat formatı aşağıdaki örnekteki gibi olmalıdır.
    // "appointmentDate": "2023-01-01 10:00"

    // Değerlendirme Formu #22 : randevu tarihi kaydederken doktorun o saatte başka bir
    // randevusu varsa veya günü müsait değilse yeni randevu kaydedilemez.
    public AppointmentDateResponse createWithDoctorAndAnimal(Long doctorId, Long animalId, AppointmentDateRequest request) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doktor bulunamadı."));

        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Hayvan bulunamadı."));

        LocalDateTime appointmentDate = request.getAppointmentDate();

        if (!isDoctorAvailable(doctorId, appointmentDate)) {
            throw new RuntimeException("Doktorun günü müsait değil veya başka bir randevusu var.");
        }

        AppointmentDate appointment = appointmentDateMapper.asEntity(request);
        appointment.setDoctor(doctor);
        appointment.setAnimal(animal);
        AppointmentDate savedAppointment = appointmentDateRepository.save(appointment);
        return appointmentDateMapper.asOutput(savedAppointment);
    }

    public AppointmentDateResponse update(Long id, AppointmentDateRequest request) {
        AppointmentDate existingAppointment = appointmentDateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + "id li Randevu Bulunamadı !!!"));

        // Güncelleme yapılacaksa AppointmentDateMapper kullanarak entity'i güncelle
        appointmentDateMapper.update(existingAppointment, request);

        // Güncellenmiş entity'i veritabanına kaydet
        AppointmentDate updatedAppointment = appointmentDateRepository.save(existingAppointment);

        return appointmentDateMapper.asOutput(updatedAppointment);
    }

    public void deleteById(Long id) {
        appointmentDateRepository.deleteById(id);
    }

    // Değerlendirme Formu #24 : randevuların tarih aralığına ve doktora göre filtrelenmesi için
    // gerekli controller ve service katmanlarının oluşturulması
    public List<AppointmentDateResponse> getAppointmentsByDateRangeAndDoctorId(LocalDate startDate, LocalDate endDate, Long doctorId) {
        List<AppointmentDate> filteredAppointments = appointmentDateRepository
                .findByAppointmentDateBetweenAndDoctorId(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX), doctorId);

        return appointmentDateMapper.asOutput(filteredAppointments);
    }

    // Değerlendirme Formu #23 : randevuların tarih aralığına ve hayvana göre filtrelenmesi için
    // gerekli controller ve service katmanlarının oluşturulması
    public List<AppointmentDateResponse> getAppointmentsByDateRangeAndAnimalId(LocalDate startDate, LocalDate endDate, Long animalId) {
        List<AppointmentDate> filteredAppointments = appointmentDateRepository
                .findByAppointmentDateBetweenAndAnimalId(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX), animalId);

        return appointmentDateMapper.asOutput(filteredAppointments);
    }


    // Değerlendirme Formu #22 : randevu tarihi kaydederken doktorun o saatte başka bir
    // randevusu varsa veya günü müsait değilse yeni randevu kaydedilemez.
    public boolean isDoctorAvailable(Long doctorId, LocalDateTime appointmentDate) {
       // Doktorun o gün müsait olup olmadığının kontrol edilmesi
        List<AvailableDate> availableDates = availableDateRepository.findByDoctorIdAndAvailableDate(doctorId, appointmentDate.toLocalDate());

        if (availableDates.isEmpty()) {
            return false; // Doktorun o günü müsait değilse false sonucu döndürülür.
        }

        // Doktorun günü müsait ise o saatte başka bir randevusu olup olmadığının kontrol edilmesi
        List<AppointmentDate> doctorAppointments = appointmentDateRepository.findByDoctorId(doctorId);
        return doctorAppointments.stream()
                .noneMatch(appointment -> appointment.getAppointmentDate().equals(appointmentDate));
    }


}





