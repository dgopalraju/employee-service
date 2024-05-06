package com.employeeservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressModel {
	
	private int addressId;
	private String street;
	private String city;
	private String district;
	private String state;
	private int pincode;
	private int empId;

}
