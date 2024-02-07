package org.dnyanyog.repo;

import java.util.List;
import java.util.Optional;

import org.dnyanyog.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface AdminCanDeleteTheCustomersRepository1 extends JpaRepository<Account, Long> {

	List<Account> findByCustomerId(Long customerId);

}