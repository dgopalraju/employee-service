package com.employeeservice.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeservice.exception.ValidationException;
import com.employeeservice.model.EmployeeModel;
import com.employeeservice.service.EmployeeService;

@RestController
@RequestMapping("employee")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@PostMapping
	ResponseEntity<String> saveEmployee(@RequestBody EmployeeModel employeeModel) {
		Optional.ofNullable(employeeModel)
				.orElseThrow(() -> new ValidationException(4001, "EmployeeModel can't be null"));
		int employeeId = employeeService.saveEmployee(employeeModel);
		return new ResponseEntity<>(
				String.format("Employee Registered successfully. Registartion Number : %s", employeeId),
				HttpStatus.CREATED);
	}

	@GetMapping("/{empId}")
	ResponseEntity<EmployeeModel> getEmployeeDetails(@PathVariable Integer empId) {
		Optional.ofNullable(empId).orElseThrow(() -> new ValidationException(2003, "EmpId can't be null"));
		EmployeeModel employeeDetails = employeeService.getEmployeeDetails(empId);
		return new ResponseEntity<>(employeeDetails, HttpStatus.OK);
	}

	@PutMapping("/{empId}")
	ResponseEntity<EmployeeModel> updateEmployeeDetails(@RequestBody EmployeeModel employeeModel,
			@PathVariable Integer empId) {
		if (employeeModel == null || empId == null) {
			throw new ValidationException(4002, "EmployeeModel (or) EmpId can't be null");
		}
		EmployeeModel updateEmployeeDetails = employeeService.updateEmployeeDetails(employeeModel, empId);
		return new ResponseEntity<EmployeeModel>(updateEmployeeDetails, HttpStatus.OK);
	}

	@DeleteMapping("/{empId}")
	ResponseEntity<String> deleteEmployee(@PathVariable int empId) {
		Optional.ofNullable(empId).orElseThrow(() -> new ValidationException(2003, "EmpId can't be null"));
		employeeService.deleteEmployee(empId);
		return new ResponseEntity<>("Eployee deleted successfully", HttpStatus.ACCEPTED);
	}

}
