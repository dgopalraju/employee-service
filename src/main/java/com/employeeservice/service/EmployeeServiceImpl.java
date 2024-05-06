package com.employeeservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.employeeservice.entity.Employee;
import com.employeeservice.exception.ValidationException;
import com.employeeservice.feignclient.AddressClient;
import com.employeeservice.model.AddressModel;
import com.employeeservice.model.EmployeeModel;
import com.employeeservice.repositary.EmployeeRepositary;

import feign.FeignException;
import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepositary employeeRepositary;

	private ModelMapper modelMapper;

	private AddressClient addressClient;

	public EmployeeServiceImpl(EmployeeRepositary employeeRepositary, ModelMapper modelMapper,
			AddressClient addressClient) {
		super();
		this.employeeRepositary = employeeRepositary;
		this.modelMapper = modelMapper;
		this.addressClient = addressClient;
	}

	@Override
	@Transactional
	public int saveEmployee(EmployeeModel employeeModel) {
		Employee employeeData = modelMapper.map(employeeModel, Employee.class);
		employeeData = employeeRepositary.save(employeeData);
		int empId = employeeData.getEmpId();
		List<AddressModel> addressModel = employeeModel.getAddress().stream().peek(n -> n.setEmpId(empId))
				.collect(Collectors.toList());
		modelMapper.map(addressModel, AddressModel[].class);
		try {
			addressClient.saveAddressDetails(addressModel);
		} catch (FeignException e) {
			throw new ValidationException(3009,
					"Address service is currently facing error. please try again or contact administative");
		}

		return addressModel.get(0).getEmpId();
	}

	@Override
	public EmployeeModel getEmployeeDetails(Integer empId) {
		Employee employee = employeeRepositary.findById(empId)
				.orElseThrow(() -> new ValidationException(3001, "EmpId is not available"));
		EmployeeModel employeeModel = new EmployeeModel();
		List<AddressModel> addressDetails = new ArrayList<>();
		try {
			addressDetails = addressClient.getAddressDetails(empId);
		} catch (FeignException e) {
			throw new ValidationException(6009,
					"Address service is currently facing error. please try again or contact administative");
		}
		employeeModel = modelMapper.map(employee, EmployeeModel.class);
		employeeModel.setAddress(addressDetails);
		return employeeModel;
	}

	@Override
	@Transactional
	public EmployeeModel updateEmployeeDetails(EmployeeModel employeeModel, Integer empId) {
		Employee updateEmployee = employeeRepositary.findById(empId)
				.orElseThrow(() -> new ValidationException(3009, "EmpId is not available"));
		BeanUtils.copyProperties(employeeModel, updateEmployee);
		employeeRepositary.save(updateEmployee);
		try {
			List<AddressModel> updateAddressDetails = addressClient.updateAddressDetails(employeeModel.getAddress(),
					empId);
			employeeModel.setAddress(updateAddressDetails);
		} catch (FeignException e) {
			throw new ValidationException(3009,
					"Address service is currently facing error. please try again or contact administative");
		}
		return employeeModel;
	}

	@Override
	@Transactional
	public void deleteEmployee(int empId) {
		employeeRepositary.deleteById(empId);
		try {
			addressClient.deleteEmployee(empId);
		} catch (FeignException e) {
			throw new ValidationException(3009,
					"Address service is currently facing error. please try again or contact administative");
		}

	}

}
