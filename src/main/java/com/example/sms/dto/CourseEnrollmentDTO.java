package com.example.sms.dto;

import com.example.sms.entity.Course;
import com.example.sms.entity.Enrollment;
import com.example.sms.entity.Student;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseEnrollmentDTO {
    private String enrollmentId;
    private LocalDate enrollmentDate;
    @NotBlank(message = "studentId cannot be blank")
    private String studentId;
    @NotBlank(message = "courseId cannot be blank")
    private String courseId;

    public static CourseEnrollmentDTO fromEnrollment(Enrollment enrollment) {
        CourseEnrollmentDTO enrollmentDTO = new CourseEnrollmentDTO();
        enrollmentDTO.setStudentId(enrollment.getStudent().getStudentId());
        enrollmentDTO.setCourseId(enrollment.getCourse().getCourseId());
        enrollmentDTO.setEnrollmentId(enrollment.getEnrollmentId());
        enrollmentDTO.setEnrollmentDate(enrollment.getEnrollmentDate());
        return enrollmentDTO;


    }

    public Enrollment toUpdateEntity(Enrollment entity, Student student, Course course) {
        if (this.studentId != null) {
            entity.setStudent(student);
        }
        if (this.courseId != null) {
            entity.setCourse(course);
        }
        return entity;
    }
}
