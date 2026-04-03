package com.example.sms.dao.repo;


import com.example.sms.entity.Enrollment;

import org.springframework.data.jpa.repository.JpaRepository;



public interface EnrollmentRepo extends JpaRepository<Enrollment, String> {
 boolean existsByCourse_CourseIdAndStudent_StudentId(String courseId, String studentId);


}
