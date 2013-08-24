package com.bouacheria.ami.service.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bouacheria.ami.domain.users.AMIUser;
import com.bouacheria.ami.repository.users.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	@Transactional
	public AMIUser save(AMIUser user){
		return repo.save(user);
	}
	
	public AMIUser findByUser(String userName){
		return repo.findByUserNameAndDeleteTimeIsNullAndAccountActiveTrue(userName);
	}
	
	public List<AMIUser> findByHospitalId(long hospitalId){
		return repo.findByHospitalIdAndDeleteTimeIsNullAndAccountActiveTrue(hospitalId);
	}

	public AMIUser findByUserAdmin(String userName){
		return repo.findByUserNameAndDeleteTimeIsNull(userName);
	}
	
	public List<AMIUser> findByHospitalIdAdmin(long hospitalId){
		return repo.findByHospitalIdAndDeleteTimeIsNull(hospitalId);
	}
	
	
}
