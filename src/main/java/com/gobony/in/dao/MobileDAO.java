package com.gobony.in.dao;

import org.springframework.data.repository.CrudRepository;

import com.gobony.in.model.CustomerVO;
import com.gobony.in.model.MobileVO;

public interface MobileDAO extends CrudRepository<MobileVO, Integer> {
	
public MobileVO findByCustomer(CustomerVO customer);

}
