package com.gobony.in.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gobony.in.dao.CustomerDAO;
import com.gobony.in.dao.MobileDAO;
import com.gobony.in.model.CustomerVO;
import com.gobony.in.model.Gender;
import com.gobony.in.model.MobileVO;
import com.gobony.in.model.PageResponse;
import com.gobony.in.model.RequestDTO;

@Service
public class CustomerService {

	@Autowired CustomerDAO customerDAO;
	@Autowired MobileDAO mobileDAO;
	
	public void saveCustomerDetails(@Valid List<CustomerVO> customer) throws Exception
	{
			for(CustomerVO i : customer)
			{
				UUID uuid = UUID.randomUUID(); 
				i.setCust_uuid(uuid.toString());
				checkDetails(i);
			}
			customerDAO.saveAll(customer);
	}

	
	public  PageResponse findAll() 
	{
	  
		  	List<CustomerVO> customerList = (List<CustomerVO>) customerDAO.findAll();
			Iterator<CustomerVO> it = customerList.iterator();
			ArrayList<RequestDTO> ar = new ArrayList<>();
			while(it.hasNext())
			{
				CustomerVO customerVO = (CustomerVO) it.next();
				RequestDTO requestDTO = new RequestDTO();
				requestDTO.setEmail(customerVO.getEmail());
				requestDTO.setFirst_name(customerVO.getFirst_name());
				requestDTO.setGender(customerVO.getGender());
				requestDTO.setLast_name(customerVO.getLast_name());
				requestDTO.setUuid(customerVO.getCust_uuid());
				
				ArrayList<String> finalMobileList = new ArrayList<>();
				
				List<MobileVO> mobileList = customerVO.getMobiles();
				for (MobileVO i : mobileList) {
					finalMobileList.add(i.getMobile_no());
					
			}
				requestDTO.setMobiles(finalMobileList);
				ar.add(requestDTO);
			}
			PageResponse pageResponse = new PageResponse();
			pageResponse.setData(ar);
	  
			return pageResponse; 
	  
	  
	  }
	  public PageResponse findByUUID(String customer_uuid) throws Exception 
	  {
		
				Optional<CustomerVO> customer_VO = customerDAO.findById(customer_uuid);
				CustomerVO customerVO = customer_VO.get();
				  
		  		ArrayList<RequestDTO> ar = new ArrayList<>();
		
				RequestDTO requestDTO = new RequestDTO();
				requestDTO.setEmail(customerVO.getEmail());
				requestDTO.setFirst_name(customerVO.getFirst_name());
				requestDTO.setGender(customerVO.getGender());
				requestDTO.setLast_name(customerVO.getLast_name());
				requestDTO.setUuid(customerVO.getCust_uuid());
				
				ArrayList<String> finalMobileList = new ArrayList<>();
				List<MobileVO> mobileList = customerVO.getMobiles();
				
				for (MobileVO i : mobileList) {
					finalMobileList.add(i.getMobile_no());
				}
				
				requestDTO.setMobiles(finalMobileList);
				ar.add(requestDTO);
			

			PageResponse pageResponse = new PageResponse();
			pageResponse.setData(ar);
  
			
			return pageResponse; 
	  
  }


	
	  
	  
	  public void checkDetails(@Valid CustomerVO customerVO) throws Exception {
			
		if(! (customerVO.getGender().equals(Gender.MALE.toString()) || customerVO.getGender().equals(Gender.FEMALE.toString())))
		{
			throw new Exception("Gender must be either male or female");
			
		}
		else if(customerVO.getMobiles() != null)
		{
			for(MobileVO i : customerVO.getMobiles())
			{
				if(i.getMobile_no().length()!=10) {
					throw new Exception("Length of mobile number must be exactly 10 digits");
				}
			}
		}
	}
}
