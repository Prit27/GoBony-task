package com.gobony.in.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.gobony.in.model.CustomerVO;

public interface CustomerDAO extends CrudRepository<CustomerVO, String>{

	
	public Optional<CustomerVO> findById(String cust_uuid);
}
