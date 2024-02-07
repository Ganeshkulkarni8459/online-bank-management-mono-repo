package org.dnyanyog.repo;

import java.util.List;

import org.dnyanyog.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface CustomerWithdrawAmountRepository extends JpaRepository<Account, Long> {
	// List<Account> findByCardNoAndAndAtmPin(String cardNo,String atmPin);

	List<Account> findByCardNoAndAtmPin(String cardNo, String atmPin);
}