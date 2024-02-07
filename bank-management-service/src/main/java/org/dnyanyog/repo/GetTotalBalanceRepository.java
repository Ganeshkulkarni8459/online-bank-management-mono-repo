package org.dnyanyog.repo;

import java.util.Optional;

import org.dnyanyog.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface GetTotalBalanceRepository extends JpaRepository<Account,Long> {
	
	Optional<Account> findByCustomerId(Long customerId);
}
