package com.employeeservice.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.employeeservice.model.AddressModel;

@FeignClient(name = "${address.service.name}", url = "${address.service.url}")
public interface AddressClient {

	@PostMapping("address")
	public void saveAddressDetails(@RequestBody List<AddressModel> addressModel);

	@GetMapping("address/{empId}")
	public List<AddressModel> getAddressDetails(@PathVariable("empId") Integer empId);

	@PutMapping("address/{empId}")
	public List<AddressModel> updateAddressDetails(@RequestBody List<AddressModel> address, @PathVariable Integer empId);

	@DeleteMapping("address/{empId}")
	public void deleteEmployee(@PathVariable int empId);

}
