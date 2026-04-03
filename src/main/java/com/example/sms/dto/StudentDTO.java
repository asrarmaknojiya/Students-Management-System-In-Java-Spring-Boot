package com.example.sms.dto;

import com.example.sms.controller.validator.MaxYear;
import com.example.sms.entity.Student;
import com.example.sms.entity.enums.Gender;
import com.example.sms.entity.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDTO {

    private String studentId;
    @NotBlank(message = "FirstName is required ")
    @Size(min = 3, message = "Atleast 3 character required")
    private String fName;
    @NotBlank(message = "lastName is required ")
    @Size(min = 3, message = "Atleast 3 character required")
    private String lName;
    @NotNull(message = "DOB is required ")
    @Past(message = "DOB can't be a future")
    @MaxYear(value = 2023)
    private LocalDate DOB;
    @NotBlank(message = "Email is required ")
    @Email(message = "Email invalid")
    private String email;
    @NotBlank(message = "PhoneNo is required ")
   @Pattern(regexp = "[0-9]{10}$",message = "Phone no is invalid")
    private String phoneNo;
    @NotNull(message = "Gender is required ")
    private Gender gender;
    private Status status;

    public StudentDTO(String studentId, String fName, String lName, LocalDate DOB, String email, String phoneNo, Gender gender, Status status) {
        this.studentId = studentId;
        this.fName = fName;
        this.lName = lName;
        this.DOB = DOB;
        this.email = email;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.status = status;
    }

    public Student toEntity() {
        return Student.builder()
                .firstName(this.fName)
                .lastName(this.lName)
                .email(this.email)
                .phoneNo(this.phoneNo)
                .DOB(this.DOB)
                .gender(this.gender)
                .build();
    }

    public static StudentDTO toDto(Student entity) {
        return new StudentDTO(
                entity.getStudentId().toString(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getDOB(),
                entity.getEmail(),
                entity.getPhoneNo(),
                entity.getGender(),
                entity.getStatus()
        );
    }

    public Student toUpdateEntity(Student entity) {

        if (this.fName != null) {
            entity.setFirstName(this.fName);
        }
        if (this.lName != null) {
            entity.setLastName(this.lName);
        }
        if (this.email != null) {
            entity.setEmail(this.email);
        }
        if (this.phoneNo != null) {
            entity.setPhoneNo(this.phoneNo);
        }
        if (this.DOB != null) {
            entity.setDOB(this.DOB);
        }
        if (this.gender != null) {
            entity.setGender(this.gender);
        }

        return entity;
    }
}
