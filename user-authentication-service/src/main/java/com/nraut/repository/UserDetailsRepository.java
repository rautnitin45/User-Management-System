package com.nraut.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nraut.model.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

	public UserDetails findByName(String name);
}
