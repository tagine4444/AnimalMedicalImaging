package com.bouacheria.ami.repository.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bouacheria.ami.domain.users.AMIUser;

public interface UserRepository extends JpaRepository<AMIUser, Long>, JpaSpecificationExecutor<AMIUser>
{
	AMIUser findByUserNameAndDeleteTimeIsNullAndAccountActiveTrue(String userName);
	List<AMIUser> findByHospitalIdAndDeleteTimeIsNullAndAccountActiveTrue(long hospitalId);

	AMIUser findByUserNameAndDeleteTimeIsNull(String userName);
	List<AMIUser> findByHospitalIdAndDeleteTimeIsNull(long hospitalId);
}