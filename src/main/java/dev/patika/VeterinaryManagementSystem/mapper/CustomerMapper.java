package dev.patika.VeterinaryManagementSystem.mapper;

import dev.patika.VeterinaryManagementSystem.dto.request.CustomerRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.CustomerResponse;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface CustomerMapper {

    Customer asEntity(CustomerRequest customerRequest);

    CustomerResponse asOutput(Customer customer);

    List<CustomerResponse> asOutput(List<Customer> customer);

    void update(@MappingTarget Customer entity, CustomerRequest request);


}
