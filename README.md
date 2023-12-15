# **Veterinary Management System**

**The veterinary management system provides an API for a veterinary clinic to manage its operations. This project is designed to be used by veterinary staff.**

## **Getting Started**

### Used Languages and Tools
- **Java 8:** _The primary programming language._
- **Spring Boot:** _A Java-based framework for application development._
- **Maven:** _Dependency management and project compilation tool._
- **PostgreSQL and MySQL:** _Database systems._
- **Git:** _Distributed version control system for versioning and collaboration._

### **Prerequisites**

1. **Java 8** or higher must be installed.
2. **Maven** must be installed.
3. **PostgreSQL** or MySQL database must be installed.
4. The application will run by default at **http://localhost:8080**.

### **Usage**

The veterinary management system API provides the following core features:

#### **Animal and Owner Management**

* Adding, updating, viewing, and deleting animals:
* Adding, updating, viewing, and deleting owners:
* Filtering owners by name:
* Filtering animals by name:
* Viewing all animals of an owner:

#### **Vaccine Management**

* Adding, updating, viewing, and deleting vaccines for animals:
* Listing vaccines for an animal based on its ID:
* Listing animals based on vaccine protection end date:

#### **Appointment Management**

* Creating, updating, viewing, and deleting appointments:
* Filtering appointments by date and doctor:
* Filtering appointments by date and animal:

#### **Veterinarian Doctor Management**

* Adding, updating, viewing, and deleting veterinarian doctors:
* Doctor's Available Dates Management
* Adding, updating, viewing, and deleting available dates for a doctor:


# Veterinary Management System - API Endpoints

**This section provides a comprehensive list of API endpoints for the Veterinary Management System. Below are the available endpoints categorized by entity.**

### Vaccine

`PUT /api/v1/vaccine/update/{id}`
Update a vaccine by vaccine ID.

`POST /api/v1/vaccine/create-with-animal/{animalId}`
Create a new vaccine using an animal ID.

`GET /api/v1/vaccine`
Get all vaccines.

`GET /api/v1/vaccine/{id}`
Get a vaccine by vaccine ID.

`GET /api/v1/vaccine/findByDateRange`
Get vaccines by date range.

`DELETE /api/v1/vaccine/delete/{id}`
Delete a vaccine with the given vaccine ID.

### Customer

`PUT /api/v1/customer/update/{id}`
Update a customer by customer ID.

`POST /api/v1/customer/create`
Create a new customer.

`GET /api/v1/customer`
Get all customers.

`GET /api/v1/customer/{id}`
Get a customer by customer ID.

`GET /api/v1/customer/{id}/with-animals`
Get customer details with their associated animals.

`GET /api/v1/customer/filter-by-name`
Filter customers by customer name.

`DELETE /api/v1/customer/delete/{id}`
Delete a customer with the given customer ID.

### AvailableDate

`PUT /api/v1/available_date/update/{id}`
Update an available date by available date ID.

`POST /api/v1/available_date/create-with-doctor/{doctorId}`
Create a new available date using a doctor ID.

`GET /api/v1/available_date`
Get all available dates.

`GET /api/v1/available_date/{id}`
Get an available date by available date ID.

`DELETE /api/v1/available_date/delete/{id}`
Delete an available date with the given available date ID.

### AppointmentDate

`PUT /api/v1/appointmentDate/update/{id}`
Update an appointment date by appointment date ID.

`POST /api/v1/appointmentDate/create-with-doctor-and-animal/{doctorId}/{animalId}`
Create a new appointment date using a doctor ID and an animal ID.

`GET /api/v1/appointmentDate`
Get all appointment dates.

`GET /api/v1/appointmentDate/{id}`
Get an appointment date by ID.

`GET /api/v1/appointmentDate/filter-by-date-range-and-doctor`
Filter appointment dates by date range and doctor ID.

`GET /api/v1/appointmentDate/filter-by-date-range-and-animal`
Filter appointment dates by date range and animal ID.

`DELETE /api/v1/appointmentDate/delete/{id}`
Delete an appointment date with the given appointment date ID.

### Doctor

`PUT /api/v1/doctor/update/{id}`
Update a doctor by doctor ID.

`POST /api/v1/doctor/create`
Create a new doctor.

`GET /api/v1/doctor`
Get all doctors.

`GET /api/v1/doctor/{id}`
Get a doctor by doctor ID.

`DELETE /api/v1/doctor/delete/{id}`
Delete a doctor with the given doctor ID.

### Animal

`PUT /api/v1/animal/update/{id}`
Update an animal by animal ID.

`POST /api/v1/animal/create-with-customer/{customerId}`
Create a new animal using a customer ID.

`GET /api/v1/animal`
Get all animals.

`GET /api/v1/animal/{id}`
Get an animal by animal ID.

`GET /api/v1/animal/{id}/with-vaccines`
Filter vaccines of an animal using animal ID.

`GET /api/v1/animal/filter-by-name`
Filter animals by animal name.

`DELETE /api/v1/animal/delete/{animalId}`
Delete an animal with the given animal ID.