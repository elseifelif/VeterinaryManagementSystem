package dev.patika.VeterinaryManagementSystem.service;

import dev.patika.VeterinaryManagementSystem.dto.request.VaccineRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.VaccineResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import dev.patika.VeterinaryManagementSystem.mapper.VaccineMapper;
import dev.patika.VeterinaryManagementSystem.repository.AnimalRepository;
import dev.patika.VeterinaryManagementSystem.repository.VaccineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaccineService {

    private final VaccineRepository vaccineRepository;
    private final VaccineMapper vaccineMapper;
    private final AnimalService animalService;
    private final AnimalRepository animalRepository;

    public List<VaccineResponse> findAll() {
       return vaccineMapper.asOutput(vaccineRepository.findAll());

    }

    // Değerlendirme Formu #21 : hayvanların aşı kayıtlarının tarih aralığına göre listelenmesi için
    // gerekli controller ve service katmanlarının oluşturulması
    public List<VaccineResponse> findByProtectionStartDateBetween(LocalDate startDate, LocalDate endDate) {
        List<Vaccine> vaccines = vaccineRepository.findByProtectionStartDateBetween(startDate, endDate);
        return vaccineMapper.asOutput(vaccines);
    }



    public Vaccine findById (Long id) {
        return vaccineRepository.findById(id).orElseThrow(() ->
                new RuntimeException(id + "id li Vaccine Bulunamadı !!!"));
    }


    public void deleteById(Long id) {
        Optional<Vaccine> vaccineFromDb = vaccineRepository.findById(id);
        if (vaccineFromDb.isPresent()) {
            vaccineRepository.delete(vaccineFromDb.get());
        } else {
            throw new RuntimeException(id + "id li aşı sistemde bulunamadı !!!");
        }
    }


    // Değerlendirme Formu #15 : hayvana ait aşı kaydetmek için gerekli controller ve
    // service katmanlarının oluşturulması

    // Değerlendirme Formu #19 : yeni aşı kaydedilirken koruyuculuk tarihi geçmişse
    // veya aynı isimde aşı daha önce kaydedilmemişse yeni aşı kaydedilebilir.
    // Koruyuculuk tarihi geçmemişse ve aynı isimde yeni aşı ekleniyor ise hata mesajı verilmelidir.

    public VaccineResponse createWithAnimal(Long animalId, VaccineRequest vaccineRequest) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException(animalId + "id li hayvan bulunamadı."));

        // Kontrol 1: Aynı isimle daha önce kaydedilen aşı var mı?
        Optional<Vaccine> existingVaccine = vaccineRepository.findByAnimalAndName(animal, vaccineRequest.getName());

        if (existingVaccine.isPresent() && existingVaccine.get().getProtectionFinishDate().isAfter(LocalDate.now())) {
            // Daha önceden kaydedilen aşının koruyuculuk tarihi geçmemişse ve aynı isimde ise yeni aşı eklenmiyor.
            throw new RuntimeException("Bu isimde bir aşı zaten kayıtlı ve koruyuculuk tarihi geçmemiştir.");
        }

        // Kontrol 2: Koruyuculuk tarihi geçmişse veya aynı isimde daha önce kaydedilmemişse, yeni aşı kaydedilebilir.
        if (existingVaccine.isEmpty() || existingVaccine.get().getProtectionFinishDate().isBefore(LocalDate.now())) {
            Vaccine vaccine = vaccineMapper.asEntity(vaccineRequest);
            vaccine.setAnimal(animal);
            Vaccine savedVaccine = vaccineRepository.save(vaccine);
            return vaccineMapper.asOutput(savedVaccine);
        } else {
            // Daha önce aynı isimle kaydedilen aşı var ve koruyuculuk tarihi geçmemişse.
            throw new RuntimeException("Bu isimde bir aşı zaten kayıtlı ve koruyuculuk tarihi geçmemiştir.");
        }
    }

    public List<Vaccine> findByAnimalId(Long id) {
        return vaccineRepository.findByAnimalId(id);
    }

    public VaccineResponse update(Long id, VaccineRequest request) {
        Optional<Vaccine> vaccineFromDb = vaccineRepository.findById(id);
        Optional<Vaccine> isVaccineExist = vaccineRepository.findByCode(request.getCode());

        if (vaccineFromDb.isEmpty()) {
            throw new RuntimeException(id + "Güncellemeye çalıştığınız aşı sistemde bulunamadı. !!!.");
        }

        if (isVaccineExist.isPresent()) {
            throw new RuntimeException("Bu aşı daha önce sisteme kayıt olmuştur !!!");
        }

        Vaccine vaccine = vaccineFromDb.get();
        vaccineMapper.update(vaccine, request);
        return vaccineMapper.asOutput(vaccineRepository.save(vaccine));
    }

}
