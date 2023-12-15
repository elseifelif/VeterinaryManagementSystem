package dev.patika.VeterinaryManagementSystem.service;

import dev.patika.VeterinaryManagementSystem.dto.request.AvailableDateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.AvailableDateResponse;
import dev.patika.VeterinaryManagementSystem.entities.AppointmentDate;
import dev.patika.VeterinaryManagementSystem.entities.AvailableDate;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import dev.patika.VeterinaryManagementSystem.mapper.AvailableDateMapper;
import dev.patika.VeterinaryManagementSystem.repository.AvailableDateRepository;
import dev.patika.VeterinaryManagementSystem.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailableDateService {

    private final AvailableDateRepository availableDateRepository;
    private final AvailableDateMapper availableDateMapper;
    private final DoctorRepository doctorRepository;

    public List<AvailableDateResponse> findAll() {
        return availableDateMapper.asOutput(availableDateRepository.findAll());
    }

    public AvailableDateResponse findById(Long id) {
        return availableDateMapper.asOutput(availableDateRepository.findById(id).orElseThrow(()
                -> new RuntimeException(id + "id li müsait gün bulunamadı !!!")));
    }

    // Değerlendirme Formu #13 : Doktor müsait günü kaydetme işlemleri için gerekli
    // controller ve service katmanlarının oluşturulması
    public AvailableDateResponse createWithDoctor(Long doctorId, AvailableDateRequest request) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doktor bulunamadı."));

        AvailableDate availableDate = availableDateMapper.asEntity(request);
        availableDate.setDoctor(doctor);

        AvailableDate savedAvailableDate = availableDateRepository.save(availableDate);
        return availableDateMapper.asOutput(savedAvailableDate);

    }

    public AvailableDateResponse update(Long id, AvailableDateRequest request) {
        AvailableDate existingAvailableDate = availableDateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + "id li müsait gün bulunamadı !!!"));

        // Güncelleme yapılacaksa AvailableDateMapper kullanarak entity'i güncelle
        availableDateMapper.update(existingAvailableDate, request);

        // Güncellenmiş entity'i veritabanına kaydet
        AvailableDate updatedAvailableDate = availableDateRepository.save(existingAvailableDate);

        return availableDateMapper.asOutput(updatedAvailableDate);
    }

    public void deleteById(Long id) {
        // Veritabanından ilgili müsait günü sil
        availableDateRepository.deleteById(id);
    }


}
