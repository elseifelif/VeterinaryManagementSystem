package dev.patika.VeterinaryManagementSystem.service;

import dev.patika.VeterinaryManagementSystem.dto.response.AnimalResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.CustomerWithAnimalResponse;
import dev.patika.VeterinaryManagementSystem.dto.request.CustomerRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.CustomerResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.mapper.AnimalMapper;
import dev.patika.VeterinaryManagementSystem.mapper.CustomerMapper;
import dev.patika.VeterinaryManagementSystem.repository.AnimalRepository;
import dev.patika.VeterinaryManagementSystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AnimalMapper animalMapper;
    private final AnimalRepository animalRepository;

    public List<CustomerResponse> findAll() {

        return customerMapper.asOutput(customerRepository.findAll());
    }

    public CustomerResponse getById(Long id) {
        return customerMapper.asOutput(customerRepository.findById(id).orElseThrow(()
                -> new RuntimeException(id + " nolu müşteri bulunamadı.")));
    }

    // Değerlendirme Formu #10 : Hayvan sahibi kaydetme işlemleri için gerekli
    // controller ve service katmanlarının oluşturulması
    public CustomerResponse create(CustomerRequest request) {
        Optional<Customer> isCustomerExist = customerRepository.findByName(request.getName());

        if (isCustomerExist.isEmpty()) {
            Customer customerSaved = customerRepository.save(customerMapper.asEntity(request));
            return customerMapper.asOutput(customerSaved);
        }
        throw new RuntimeException("Bu müşteri daha önce sisteme kayıt olmuştur !!!");
    }

    public CustomerResponse update(Long id, CustomerRequest request) {
        Optional<Customer> customerFromDb = customerRepository.findById(id);
        Optional<Customer> isCustomerExist = customerRepository.findByName(request.getName());

        if (customerFromDb.isEmpty()) {
            throw new RuntimeException(id + " nolu müşteri bulunamadı.");
        }

        if (isCustomerExist.isPresent()) {
            throw new RuntimeException("Bu müşteri daha önce sisteme kayıt olmuştur !!!");
        }
        Customer customer = customerFromDb.get();
        customerMapper.update(customer, request);
        return customerMapper.asOutput(customerRepository.save(customer));
    }

    public void deleteById(Long id) {
        Optional<Customer> customerFromDb = customerRepository.findById(id);

        if (customerFromDb.isPresent()) {
            customerRepository.delete(customerFromDb.get());
        } else {
            throw new RuntimeException(id + " nolu müşteri bulunamadı.");
        }
    }

    // Değerlendirme Formu #18 : id numarası yazılan hayvan sahibinin sistemde kayıtlı hayvanlarını
    // listeleyen controller ve service katmanlarının oluşturulması
    public CustomerWithAnimalResponse getCustomerWithAnimals(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException(customerId + " nolu müşteri bulunamadı."));

        List<Animal> animals = animalRepository.findByCustomer(customer);

        List<AnimalResponse> animalResponses = animalMapper.asOutput(animals);

        return new CustomerWithAnimalResponse(
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getMail(),
                customer.getAddress(),
                customer.getCity(),
                animalResponses
        );
    }

    // Değerlendirme Formu #17 : hayvan sahiplerinin isme göre filtrelenmesi için gerekli
    // controller ve service katmanlarının oluşturulması
    public List<CustomerResponse> getCustomersByName(String name) {
        List<Customer> customers = customerRepository.findByNameContainingIgnoreCase(name);
        return customers.stream()
                .map(customerMapper::asOutput)
                .collect(Collectors.toList());
    }

}
