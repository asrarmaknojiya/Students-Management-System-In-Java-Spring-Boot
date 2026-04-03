package com.example.sms.services;

import com.example.sms.dao.DepartmentDao;
import com.example.sms.dao.InstructorDao;
import com.example.sms.dto.DepartmentDTO;
import com.example.sms.dto.ResponseModel;
import com.example.sms.entity.Department;
import com.example.sms.entity.Instructor;
import com.example.sms.entity.enums.Status;
import com.example.sms.util.APIMessage;
import com.example.sms.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DepartmentService {
    @Autowired
    public DepartmentDao departmentDao;
    @Autowired
    public InstructorDao instructorDao;

    public ResponseModel insetDepartment(DepartmentDTO dto) {
        boolean departmentExists = departmentDao.existByDepartmentName(dto.getDepartmentName());
        if (departmentExists) {
            return ResponseModel.conflict(
                    APIMessage.DEPARTMENT_ALREADY_PRESENT,
                    null
            );
        }

        Instructor hod = null;
        if (dto.getHOD() != null) {
            hod = instructorDao.findById(dto.getHOD());
            if (hod == null) {
                return ResponseModel.not_found(
                        APIMessage.INSTRUCTOR_NOT_FOUND,
                        null
                );
            }
        }

        List<Instructor> instructors = new ArrayList<>();
        if (dto.getInstructors() != null && !dto.getInstructors().isEmpty()) {
            for (String instructorId : dto.getInstructors()) {
                Instructor instructor = instructorDao.findById(instructorId);
                if (instructor == null) {
                    return ResponseModel.not_found(
                            APIMessage.INSTRUCTOR_NOT_FOUND,
                            null
                    );
                }
                instructors.add(instructor);
            }
        }

        Department department = dto.toEntity(hod, instructors);
        department = departmentDao.save(department);

        return ResponseModel.created(
                APIMessage.DEPARTMENT_CREATED,
                DepartmentDTO.toDTO(department)
        );
    }


    public ResponseModel getAllDepartment(int pageSize, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        Page<Department> departments = departmentDao.findAllByStatus(Status.ACTIVE, pageable);
        List<DepartmentDTO> dtos = departments
                .getContent()
                .stream()
                .map(DepartmentDTO::toDTO)
                .toList();

        Map<String, Object> result = new HashMap<>();
        result.put("data", dtos);
        result.put("pageResult", Utils.preparePageResult(departments));

        return ResponseModel.success(
                APIMessage.DEPARTMENT_FOUND,
                result
        );

    }

    public ResponseModel getDepartmentById(String departmentId){
        Department department=departmentDao.findById(departmentId);
        if (department==null){
        return    ResponseModel.not_found(
                    APIMessage.DEPARTMENT_NOT_FOUND,
                    null
            );
        }
        return ResponseModel.success(
                APIMessage.DEPARTMENT_FOUND,
                DepartmentDTO.toDTO(department)
        );
    }


    public ResponseModel deleteDepartmentById(String departmentId) {
        Department department = departmentDao.findById(departmentId);
        if (department == null) {
            return ResponseModel.not_found(
                    APIMessage.DEPARTMENT_NOT_FOUND,
                    null
            );
        }
        department.setStatus(Status.DELETED);
        departmentDao.save(department);
        return ResponseModel.success(
                APIMessage.DEPARTMENT_DELETED,
                DepartmentDTO.toDTO(department)
        );
    }

    public ResponseModel updateDepartmentById(String departmentId, DepartmentDTO dto) {
        Department department = departmentDao.findById(departmentId);
        if (department == null) {
            return ResponseModel.not_found(
                    APIMessage.DEPARTMENT_NOT_FOUND,
                    null
            );
        }
        if (dto.getDepartmentName() != null && !dto.getDepartmentName().equals(department.getDepartmentName())) {
            boolean departmentExists = departmentDao.existsByDepartmentName(dto.getDepartmentName());
            if (departmentExists) {
                return ResponseModel.conflict(
                        APIMessage.DEPARTMENT_ALREADY_PRESENT,
                        null
                );
            }
        }

        Instructor hod = null;
        if (dto.getHOD() != null) {
            hod = instructorDao.findById(dto.getHOD());
            if (hod == null) {
                return ResponseModel.not_found(
                        APIMessage.INSTRUCTOR_NOT_FOUND,
                        null
                );
            }
        }
        List<Instructor> instructors = new ArrayList<>();
        if (dto.getInstructors() != null && !dto.getInstructors().isEmpty()) {
            for (String instructorId : dto.getInstructors()) {
                Instructor instructor = instructorDao.findById(instructorId);
                if (instructor == null) {
                    ResponseModel.not_found(
                            APIMessage.INSTRUCTOR_NOT_FOUND,
                            null);
                }
                instructors.add(instructor);
            }

        }

        department=dto.toUpdateEntity(department,hod,instructors);
      department=  departmentDao.save(department);
      return ResponseModel.success(
              APIMessage.DEPARTMENT_UPDATED,
              DepartmentDTO.toDTO(department)
      );
    }

}
