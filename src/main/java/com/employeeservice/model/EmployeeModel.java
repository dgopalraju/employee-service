package com.employeeservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {
	
	private String empName;
	private String empNumber;
	private String empEmail;
	List<AddressModel> address;

}
