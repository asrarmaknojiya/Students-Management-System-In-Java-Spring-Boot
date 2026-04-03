package com.example.sms.dto;

import com.example.sms.entity.Department;
import com.example.sms.entity.Instructor;
import com.example.sms.entity.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {


    private String departmentId;
    private String departmentName;
    private Status status;
    private String HOD;
    private List<String> instructors;

    public Department toEntity(Instructor hod, List<Instructor> instructors) {
        return Department.builder()
                .departmentName(this.departmentName)
                .HOD(hod)
                .instructors(instructors)
                .build();
    }

    public static DepartmentDTO toDTO(Department department) {
        String hodId = null;
        if (department.getHOD() != null) {
            hodId = department.getHOD().getInstructorId();

        }

        List<String> instructorsIds = new ArrayList<>();
        if (department.getInstructors() != null) {
            for (Instructor instructor : department.getInstructors()) {
                instructorsIds.add(instructor.getInstructorId());
            }
        }

        return DepartmentDTO.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .status(department.getStatus())
                .HOD(hodId)
                .instructors(instructorsIds)
                .build();
    }


    public Department toUpdateEntity(Department department,Instructor hod,List<Instructor> instructors){
        if (this.departmentName !=null){
            department.setDepartmentName(this.departmentName);
        }
        if (this.status !=null){
            department.setStatus(this.status);
        }
        if (hod !=null){
            department.setHOD(hod);
        }
        if (instructors !=null && instructors.isEmpty()){
            department.setInstructors(instructors);
        }
        return department;
    }


}
