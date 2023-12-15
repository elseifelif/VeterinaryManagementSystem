package dev.patika.VeterinaryManagementSystem.controller;

import dev.patika.VeterinaryManagementSystem.dto.request.AvailableDateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.AvailableDateResponse;
import dev.patika.VeterinaryManagementSystem.service.AvailableDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/available_date")
public class AvailableDateController {

    private final AvailableDateService availableDateService;

    @Autowired
    public AvailableDateController(AvailableDateService availableDateService){
        this.availableDateService = availableDateService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDateResponse> findAll() {
        return availableDateService.findAll();
    }

    @GetMapping ("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDateResponse getById(@PathVariable Long id) {

        return availableDateService.findById(id);
    }

    // Değerlendirme Formu #13 : Doktor müsait günü kaydetme işlemleri için gerekli
    // controller ve service katmanlarının oluşturulması
    @PostMapping("/create-with-doctor/{doctorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AvailableDateResponse saveWithDoctor(
            @PathVariable Long doctorId,
            @RequestBody AvailableDateRequest availableDateRequest
    ) {
        return availableDateService.createWithDoctor(doctorId, availableDateRequest);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDateResponse update(@PathVariable Long id, @RequestBody AvailableDateRequest request) {
        return availableDateService.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        availableDateService.deleteById(id);
    }


}
