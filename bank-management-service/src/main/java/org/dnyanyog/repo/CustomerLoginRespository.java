package org.dnyanyog.repo;

import java.util.List;

import org.dnyanyog.entity.CustomerResgistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface CustomerLoginRespository extends JpaRepository<CustomerResgistration,Long> {
	
	List<CustomerResgistration> findByEmailIdAndPassword(String emailId,String password);

}
