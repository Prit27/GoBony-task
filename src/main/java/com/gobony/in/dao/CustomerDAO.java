package com.gobony.in.dao;

import org.springframework.data.repository.CrudRepository;

import com.gobony.in.model.CustomerVO;

public interface CustomerDAO extends CrudRepository<CustomerVO, String>{
	
	
}
