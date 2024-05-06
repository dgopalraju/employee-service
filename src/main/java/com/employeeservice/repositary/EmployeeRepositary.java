package com.employeeservice.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeservice.entity.Employee;

public interface EmployeeRepositary extends JpaRepository<Employee, Integer> {

}
