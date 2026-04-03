package com.example.sms.controller;

import com.example.sms.dto.DepartmentDTO;
import com.example.sms.dto.ResponseModel;
import com.example.sms.entity.Department;
import com.example.sms.services.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/sms/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/insert")
    public ResponseModel insertDepartment(@RequestBody DepartmentDTO dto) {
        return departmentService.insetDepartment(dto);
    }

    @GetMapping("/get-all")
    public ResponseModel getAllDepartments(@RequestParam(required = false, defaultValue = "10") int pageSize,
                                           @RequestParam(required = false, defaultValue = "1") int pageNo) {
        return departmentService.getAllDepartment(pageSize, pageNo);
    }
    @GetMapping("get/by/{departmentId}")
    public ResponseModel getDepartmentById(@PathVariable String departmentId){
        return departmentService.getDepartmentById(departmentId);
    }

    @DeleteMapping("/delete/{departmentId}")
    public ResponseModel deleteDepartment(@PathVariable String departmentId) {
        return departmentService.deleteDepartmentById(departmentId);
    }

    @PutMapping("/update/by/{departmentId}")
    public ResponseModel updateDepartmentById(@PathVariable String departmentId,
                                              @RequestBody DepartmentDTO dto){
        return departmentService.updateDepartmentById(departmentId,dto);
    }



}
