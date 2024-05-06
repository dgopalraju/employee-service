package com.employeeservice.service;

import com.employeeservice.model.EmployeeModel;

public interface EmployeeService {

	int saveEmployee(EmployeeModel employeeModel);

	EmployeeModel getEmployeeDetails(Integer empId);

	EmployeeModel updateEmployeeDetails(EmployeeModel employeeModel, Integer empId);

	void deleteEmployee(int empId);

}
