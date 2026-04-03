package com.example.sms.controller;

import com.example.sms.dto.CourseEnrollmentDTO;
import com.example.sms.dto.ResponseModel;
import com.example.sms.entity.Enrollment;
import com.example.sms.services.CourseEnrollmentServices;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/sms/api/course/enrollment")
public class CourseEnrollmentController {

    @Autowired
    private CourseEnrollmentServices courseEnrollmentServices;


    @PostMapping("/insert")
  public ResponseModel insertCourseEntrollment(@RequestBody @Valid CourseEnrollmentDTO dto){
return courseEnrollmentServices.insertcourseEnrollment(dto);
    }

    @GetMapping("/get-all")
    public ResponseModel getAllEntrollment(@RequestParam int PageNo,@RequestParam int PageSize){
        return courseEnrollmentServices.getAllEntrollment(PageNo,PageSize);
    }

    @GetMapping("/get/by/{enrollmentId}")
    public ResponseModel getEnrollmentById(@PathVariable String enrollmentId){
        return courseEnrollmentServices.getEnrollmentById(enrollmentId);
    }
    @PutMapping("/update/by/{enrollmentId}")
    public ResponseModel updateEnrollmentById(@PathVariable String enrollmentId,
                                              @RequestBody CourseEnrollmentDTO dto){
        return courseEnrollmentServices.updateEnrollmentById(enrollmentId,dto);

    }


    @DeleteMapping("/delete/by/{enrollmentId}")
    public ResponseModel deleteEmrollmentById(@PathVariable String enrollmentId){
        return courseEnrollmentServices.deleteEnrollmentById(enrollmentId);
    }
}
