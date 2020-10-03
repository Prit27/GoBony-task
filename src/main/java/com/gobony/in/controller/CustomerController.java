package com.gobony.in.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gobony.in.dao.MobileDAO;
import com.gobony.in.model.CustomerVO;
import com.gobony.in.model.PageResponse;
import com.gobony.in.service.CustomerService;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class CustomerController {
	
	@Autowired CustomerService customerService;
	
	@Autowired MobileDAO mobileDAO;
	
	@RequestMapping(value="/customers",method = RequestMethod.PUT)
	public ResponseEntity<?> insertCustomerDetails(@Valid @RequestBody List<CustomerVO> customer,BindingResult bindingResult) throws Exception {

		
		if (bindingResult.hasErrors()) {
				return new ResponseEntity<>(bindingResult.getFieldErrors().toString(),HttpStatus.BAD_REQUEST);
		}
	
		customerService.saveCustomerDetails(customer);
		return new ResponseEntity<>(HttpStatus.CREATED);

		
}
	
	@RequestMapping(value="/customers",method=RequestMethod.GET)
	public ResponseEntity<?> getAllDetails()
	{
		
		PageResponse pageResponse = customerService.findAll();
		
		return new ResponseEntity<>(pageResponse,HttpStatus.OK);
		
	}
	@RequestMapping(value="/customers/{customer_uuid}")
	public ResponseEntity<?> getOneCustomerDetails(@PathVariable String customer_uuid) throws Exception {
	
		PageResponse pageResponse = customerService.findByUUID(customer_uuid);
		
		Object[] objArray = pageResponse.getData().toArray();
		return new ResponseEntity<>(objArray,HttpStatus.OK);

	}

}
