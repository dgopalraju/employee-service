package com.employeeservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "employee", schema = "employee")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	@Id
	@GeneratedValue(generator = "EMP_SEQ", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(sequenceName = "EMP_SEQ", name = "EMP_SEQ", initialValue = 1000, allocationSize = 1)
	private int empId;
	private String empName;
	private String empNumber;
	private String empEmail;

}
