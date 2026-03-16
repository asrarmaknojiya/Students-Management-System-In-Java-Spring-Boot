package com.example.sms.controller;

import com.example.sms.dto.InstructorDTO;
import com.example.sms.dto.ResponseModel;
import com.example.sms.entity.Instructor;
import com.example.sms.services.InstructorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/sms/api/instructor")
public class InstructorController {

    @Autowired
    public InstructorService instructorService;

    @PostMapping("/insert")
    public ResponseModel insertInstructor(@RequestBody InstructorDTO dto) {
        return instructorService.insertInstructor(dto);
    }

    @GetMapping("/get-all")
    public ResponseModel getAllInstructor(@RequestParam(required = false, defaultValue = "10") int pageSize,
                                          @RequestParam(required = false, defaultValue = "1") int pageNo) {
        return instructorService.getAllInstructor(pageSize, pageNo);
    }

    @GetMapping("/by/{id}")
    public ResponseModel getInstructorById(@PathVariable String id){
        return  instructorService.getInstructorById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseModel updateInstructor(@PathVariable String id , @RequestBody InstructorDTO dto){
        return instructorService.updateInstructor(id,dto);

    }

    @DeleteMapping("/delete/{id}")
    public  ResponseModel deleteInstructor(@PathVariable String id){
        return instructorService.deleteInstructor(id);
    }

    @GetMapping("/search")
    public ResponseModel searchInstructor(@RequestParam String keyWord,@RequestParam(required = false, defaultValue = "10") int pageSize,
                                          @RequestParam(required = false, defaultValue = "1") int pageNo){
        return instructorService.searchInstructor(keyWord,pageSize,pageNo);
    }
}
