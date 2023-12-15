package dev.patika.VeterinaryManagementSystem.controller;

import dev.patika.VeterinaryManagementSystem.dto.request.CustomerRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.CustomerResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.CustomerWithAnimalResponse;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")

public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> findAll() {
        return customerService.findAll();
    }

    // Değerlendirme Formu #18 : id numarası yazılan hayvan sahibinin sistemde kayıtlı hayvanlarını
    // listeleyen controller ve service katmanlarının oluşturulması
    @GetMapping("/{id}/with-animals")
    @ResponseStatus(HttpStatus.OK)
    public CustomerWithAnimalResponse getCustomerWithAnimals(@PathVariable Long id) {
        return customerService.getCustomerWithAnimals(id);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getById(@PathVariable Long id ) {
        return customerService.getById(id);
    }

   // Değerlendirme Formu #10 : Hayvan sahibi kaydetme işlemleri için gerekli
   // controller ve service katmanlarının oluşturulması
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse save(@RequestBody CustomerRequest customer) {
        return customerService.create(customer);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse update(@PathVariable Long id, @RequestBody CustomerRequest request) {
        return customerService.update(id, request);
    }

    // Değerlendirme Formu #17 : hayvan sahiplerinin isme göre filtrelenmesi için gerekli
    // controller ve service katmanlarının oluşturulması
    @GetMapping("/filter-by-name")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> getCustomersByName(@RequestParam String name) {
        return customerService.getCustomersByName(name);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        customerService.deleteById(id);
    }

}
