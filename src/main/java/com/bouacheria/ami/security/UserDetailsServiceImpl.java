package com.bouacheria.ami.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bouacheria.ami.domain.hospital.Hospital;
import com.bouacheria.ami.domain.users.AMIUser;
import com.bouacheria.ami.service.hospital.HospitalService;
import com.bouacheria.ami.service.users.UserService;

@Service("userDetailsService") 
public class UserDetailsServiceImpl  implements UserDetailsService {

	  @Autowired
	  private UserService userService;

	  @Autowired
	  private HospitalService hospitalService;

	  @Transactional(readOnly = true)
	  public AmiUserDetail loadUserByUsername(String username)
	      throws UsernameNotFoundException, DataAccessException {

	    AMIUser user = userService.findByUser(username);
	    
	    if (user == null)
	      throw new UsernameNotFoundException("user not found");

	    return buildUserFromUserEntity(user);
	  }
	  
	  
	  @Transactional(readOnly = true)
	  AmiUserDetail buildUserFromUserEntity(AMIUser amiUser) {

		Hospital hospital = hospitalService.findById(amiUser.getHospitalId());
	    String username = amiUser.getUserName();
	    String password = amiUser.getPwd();
	    boolean isAccountActive = amiUser.isAccountActive();
	    boolean accountNonExpired = isAccountActive;
	    boolean credentialsNonExpired = isAccountActive;
	    boolean accountNonLocked = isAccountActive;
	  

	    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//	    for (SecurityRoleEntity role : hospital.getRoles()) {
//	      authorities.add(new GrantedAuthorityImpl(role.getRoleName()));
//	    }

	    authorities.add(new GrantedAuthorityImpl(amiUser.getPermission()));
	    AmiUserDetail user = new AmiUserDetail(username, password, isAccountActive,
	      accountNonExpired, credentialsNonExpired, accountNonLocked, authorities, hospital,amiUser.getEmail());
	    return user;
	  }


	  
	}