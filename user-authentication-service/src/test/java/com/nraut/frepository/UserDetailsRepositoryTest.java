package com.nraut.frepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import com.nraut.model.UserDetails;
import com.nraut.repository.UserDetailsRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserDetailsRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	
	@Test
	@Rollback(false)
	public void testCreateUsers() {
	    UserDetails userDetails = userDetailsRepository.save(new UserDetails(Long.valueOf(109), "nitin1", "salaried"));
	    assertThat(userDetails.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testFindUserByName() {
		UserDetails userDetails = userDetailsRepository.findByName("nitin1");
		assertThat(userDetails.getName()).isEqualTo("nitin1");
	}
	
	@Test
	public void testListUsers() {
	    List<UserDetails> userDetails = (List<UserDetails>) userDetailsRepository.findAll();
	    assertThat(userDetails).size().isGreaterThan(0);
	}
	
	@Test
	@Rollback(false)
	public void testUpdateUser() {
		UserDetails userDetails = userDetailsRepository.findByName("nitin1");
		userDetails.setType("admin");
	     
	    userDetailsRepository.save(userDetails);
	     
	    UserDetails updatedUserDetails = userDetailsRepository.findByName("nitin1");	     
	    assertThat(updatedUserDetails.getType()).isEqualTo("admin");
	}
	
	@Test
	@Rollback(false)
	public void testDeleteUser() {
		UserDetails userDetails = userDetailsRepository.findByName("nitin1");
	     
		userDetailsRepository.deleteById(userDetails.getId());	     
	    UserDetails deletedUserDetails  = userDetailsRepository.findByName("nitin1");
	     
	    assertThat(deletedUserDetails).isNull();     	     
	}
}	
	
