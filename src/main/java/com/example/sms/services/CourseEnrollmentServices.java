package com.example.sms.services;


import com.example.sms.dao.CourseDao;
import com.example.sms.dao.CourseEnrollmentDao;
import com.example.sms.dao.StudentDao;
import com.example.sms.dto.CourseEnrollmentDTO;
import com.example.sms.dto.ResponseModel;
import com.example.sms.entity.Course;
import com.example.sms.entity.Enrollment;
import com.example.sms.entity.Student;
import com.example.sms.exception.DuplicateExceptionResource;
import com.example.sms.exception.MaxLimitExceptionResource;
import com.example.sms.exception.NotFoundExceptionResource;
import com.example.sms.util.APIMessage;
import com.example.sms.util.Utils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseEnrollmentServices {

    @Autowired
    public CourseEnrollmentDao courseEnrollmentDao;
    @Autowired
    public CourseDao courseDao;
    @Autowired
    public StudentDao studentDao;


    @Transactional(rollbackOn = Exception.class)
    public ResponseModel insertcourseEnrollment(CourseEnrollmentDTO dto) {
        Course course = courseDao.findById(dto.getCourseId());
        if (course == null) {
            throw new NotFoundExceptionResource(APIMessage.COURSE_NOT_FOUND);

        } else if (course.getCurrentEnrollment() == course.getMaxEnrollment()) {
            throw new MaxLimitExceptionResource(APIMessage.COURSE_ENROLLMENT_MAX_LIMIT_REACHED);
        }
        Student student = studentDao.findById(dto.getStudentId());
        if (student == null) {
            throw new NotFoundExceptionResource(APIMessage.STUDENT_NOT_FOUND);

        }

        boolean studentAlreadyEnrolled = courseEnrollmentDao.existByCourseIdStudentId(course.getCourseId(), student.getStudentId());
        if (studentAlreadyEnrolled) {
            throw new DuplicateExceptionResource(APIMessage.STUDENT_ALREADY_ENROLLED);
        }

        course.setCurrentEnrollment(course.getCurrentEnrollment() + 1);
        courseDao.save(course);


        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setStudent(student);

        courseEnrollmentDao.save(enrollment);


        return ResponseModel.created(
                APIMessage.COURSE_ENROLLMENT_CREATED,
                CourseEnrollmentDTO.fromEnrollment(enrollment)
        );
    }


    public ResponseModel getAllEntrollment(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<Enrollment> enrollments = courseEnrollmentDao.findAll(pageable);

        List<CourseEnrollmentDTO> enrollmentDTOS = enrollments.stream().
                map(CourseEnrollmentDTO::fromEnrollment).toList();


        Map<String, Object> result = new HashMap<>();
        result.put("data", enrollmentDTOS);
        result.put("pageResult", Utils.preparePageResult(enrollments));
        return ResponseModel.success(APIMessage.COURSE_ENROLLMENT_FOUND, result);
    }

    public ResponseModel getEnrollmentById(String enrollmentId) {
        Enrollment enrollment = courseEnrollmentDao.findById(enrollmentId);
        if (enrollment == null) {
            throw new NotFoundExceptionResource(APIMessage.COURSE_ENROLLMENT_NOT_FOUND);


        }
        return ResponseModel.success(
                APIMessage.COURSE_ENROLLMENT_FOUND, CourseEnrollmentDTO.fromEnrollment(enrollment)
        );
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseModel deleteEnrollmentById(String enrollmentId) {
        Enrollment enrollment = courseEnrollmentDao.findById(enrollmentId);
        if (enrollment == null) {
            throw new NotFoundExceptionResource(APIMessage.COURSE_ENROLLMENT_NOT_FOUND);


        }
        courseEnrollmentDao.deleteById(enrollmentId);

        return ResponseModel.success(
                APIMessage.COURSE_ENROLLMENT_DELETED,
                CourseEnrollmentDTO.fromEnrollment(enrollment)
        );


    }

    public ResponseModel updateEnrollmentById(String enrollmentId, CourseEnrollmentDTO dto) {
        Enrollment enrollment = courseEnrollmentDao.findById(enrollmentId);
        if (enrollment == null) {
            throw new NotFoundExceptionResource(APIMessage.COURSE_ENROLLMENT_NOT_FOUND);


        }
        Student student = null;
        if (dto.getStudentId() != null && !dto.getStudentId().equals(enrollment.getStudent().getStudentId())) {
            student = studentDao.findById(dto.getStudentId());
            if (student == null) {
                throw new NotFoundExceptionResource(
                        APIMessage.STUDENT_NOT_FOUND
                );
            }

            String courseId = enrollment.getCourse().getCourseId();
            boolean studentAlreadyEnrolled = courseEnrollmentDao
                    .existByCourseIdStudentId(dto.getStudentId(), courseId);
            if (studentAlreadyEnrolled) {
                return ResponseModel.conflict(
                        APIMessage.STUDENT_ALREADY_ENROLLED, null
                );
            }

        }


        Course course = null;
        if (dto.getCourseId() != null && !dto.getCourseId().equals(enrollment.getCourse().getCourseId())) {
            course = courseDao.findById(dto.getCourseId());
            if (course == null) {
                return ResponseModel.not_found(
                        APIMessage.COURSE_ENROLLMENT_FOUND, null
                );
            }

            String studentId=enrollment.getStudent().getStudentId();
            boolean CourseAlreadyEnrolled=courseEnrollmentDao
                    .existByCourseIdStudentId(studentId,dto.getCourseId());
            if (CourseAlreadyEnrolled){
                return ResponseModel.conflict(
                        APIMessage.STUDENT_ALREADY_ENROLLED,null
                );
            }

        }
        enrollment=dto.toUpdateEntity(enrollment,student,course);
        courseEnrollmentDao.save(enrollment);

        return ResponseModel.success(
                APIMessage.COURSE_ENROLLMENT_UPDATED,
                CourseEnrollmentDTO.fromEnrollment(enrollment)
        );
    }
}
