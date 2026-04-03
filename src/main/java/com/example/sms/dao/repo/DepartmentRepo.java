package com.example.sms.dao.repo;

import com.example.sms.entity.Department;
import com.example.sms.entity.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepo extends JpaRepository<Department, String> {

 boolean existsByDepartmentName(String departmentName);

 Page<Department> findAllDepartmentByStatus(Status status,Pageable pageable);
 boolean existsDepartmentByDepartmentName(String departmentName);
}
