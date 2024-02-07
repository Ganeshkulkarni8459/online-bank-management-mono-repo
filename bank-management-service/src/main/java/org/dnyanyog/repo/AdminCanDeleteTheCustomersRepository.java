package org.dnyanyog.repo;

import java.util.List;

import org.dnyanyog.entity.CustomerResgistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface AdminCanDeleteTheCustomersRepository extends JpaRepository<CustomerResgistration, Long> {

	List<CustomerResgistration> findByCustomerId(Long customerId);

}
