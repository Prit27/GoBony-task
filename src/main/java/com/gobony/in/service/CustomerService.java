package com.gobony.in.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
	
	public void saveCustomerDetails(RequestDTO requestDTO)
	{
		UUID uuid = UUID.randomUUID(); 
		
		CustomerVO customerVO = new CustomerVO();
		customerVO.setFirst_name(requestDTO.getFirst_name());
		customerVO.setLast_name(requestDTO.getLast_name());
		customerVO.setCust_uuid(uuid.toString());
		customerVO.setEmail(requestDTO.getEmail());
		customerVO.setGender(requestDTO.getGender());
		
		MobileVO mobileVO = new MobileVO();
		mobileVO.setMobile_no(requestDTO.getMobiles());
		mobileVO.setCustomerVO(customerVO);
		
		customerDAO.save(customerVO);
		mobileDAO.save(mobileVO);
		
		
		
	}

	public PageResponse findAll() {
		
		List<MobileVO> customerList = (List<MobileVO>) mobileDAO.findAll();
		Iterator<MobileVO> it = customerList.iterator();
		ArrayList<RequestDTO> ar = new ArrayList<>();
		while(it.hasNext())
		{
			MobileVO mobileVO = (MobileVO) it.next();
			RequestDTO requestDTO = new RequestDTO();
			requestDTO.setEmail(mobileVO.getCustomerVO().getEmail());
			requestDTO.setFirst_name(mobileVO.getCustomerVO().getFirst_name());
			requestDTO.setGender(mobileVO.getCustomerVO().getGender());
			requestDTO.setLast_name(mobileVO.getCustomerVO().getLast_name());
			requestDTO.setMobiles(mobileVO.getMobile_no());
			requestDTO.setUuid(mobileVO.getCustomerVO().getCust_uuid());
			ar.add(requestDTO);
		}
		PageResponse pageResponse = new PageResponse();
		pageResponse.setData(ar);
		
	
		return pageResponse;
	}

	public PageResponse findByUUID(String customer_uuid) throws Exception {
		
		PageResponse pageResponse = null;
		try {
			CustomerVO customerVO = new CustomerVO();
			customerVO.setCust_uuid(customer_uuid);
			
			MobileVO mobileVO =  mobileDAO.findByCustomerVO(customerVO);
			ArrayList<RequestDTO> ar = new ArrayList<>();
			
				RequestDTO requestDTO = new RequestDTO();
				requestDTO.setEmail(mobileVO.getCustomerVO().getEmail());
				requestDTO.setFirst_name(mobileVO.getCustomerVO().getFirst_name());
				requestDTO.setGender(mobileVO.getCustomerVO().getGender());
				requestDTO.setLast_name(mobileVO.getCustomerVO().getLast_name());
				requestDTO.setMobiles(mobileVO.getMobile_no());
				requestDTO.setUuid(mobileVO.getCustomerVO().getCust_uuid());
				ar.add(requestDTO);
			
			pageResponse = new PageResponse();
			pageResponse.setData(ar);
		} catch (Exception e) {
			throw new Exception("Customer not found");
		}
		
	
		return pageResponse;
	}

	public void checkDetails(@Valid RequestDTO requestDTO) throws Exception {
		if(! (requestDTO.getGender().equals(Gender.MALE.toString()) || requestDTO.getGender().equals(Gender.FEMALE.toString())))
		{
			throw new Exception("Gender must be either male or female");
			
		}
		else if(requestDTO.getMobiles() != null){
			for(String i : requestDTO.getMobiles())
			{
				if(i.length()!=10) {
					throw new Exception("Length of mobile number must be exactly 10 digits");
				}
			}
		}
	}
}
