package com.gobony.in.controller;

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
import com.gobony.in.model.PageResponse;
import com.gobony.in.model.RequestDTO;
import com.gobony.in.service.CustomerService;

import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class CustomerController {
	
	@Autowired CustomerService customerService;
	
	@Autowired MobileDAO mobileDAO;
	
	@RequestMapping(value="/customers",method = RequestMethod.PUT)
	public ResponseEntity<?> insertCustomerDetails(@Valid @RequestBody RequestDTO requestDTO,BindingResult bindingResult) {
{
	if (bindingResult.hasErrors()) {
	return new ResponseEntity<>(bindingResult.getFieldErrors().toString(),HttpStatus.BAD_REQUEST);
	}
	
	try {
		customerService.checkDetails(requestDTO);
		customerService.saveCustomerDetails(requestDTO);
			
	} catch (Exception e) {
		return new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.BAD_REQUEST);
	}
		
		return new ResponseEntity<>(HttpStatus.CREATED);
}
		
	}
	
	@RequestMapping(value="/customers",method=RequestMethod.GET)
	public ResponseEntity<PageResponse> getAllDetails()
	{
		
		PageResponse pageResponse = customerService.findAll();
		
		return new ResponseEntity<>(pageResponse,HttpStatus.OK);
		
	}
	@RequestMapping(value="/customers/{customer_uuid}")
	public ResponseEntity<?> getOneCustomerDetails(@PathVariable String customer_uuid) {
		PageResponse pageResponse = null ;
		try {
			pageResponse = customerService.findByUUID(customer_uuid);
				
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
			
		}
		
		return new ResponseEntity<>(pageResponse,HttpStatus.OK);
	}

}
