package com.nraut.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nraut.model.UserDetails;
import com.nraut.repository.UserDetailsRepository;

/**
 * @author Nitin
 *
 */
@Service
@Transactional
public class UserDetailService {

	@Autowired
	UserDetailsRepository userRepository;
	
	public List<UserDetails> listAll(){
		return userRepository.findAll();
	}
	
	public void save(UserDetails userDetails){
		userRepository.save(userDetails);
	}
	
	public UserDetails get(long id){
		return userRepository.findById(id).get();
	}
	
	public void delete(long id){
		userRepository.deleteById(id);
	}
}
