package org.dnyanyog.repo;

import java.util.List;

import org.dnyanyog.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface CustomerDepositAmountRepository extends JpaRepository<Account, String> {

	List<Account> findByCardNoAndAtmPin(String atmPin, String cardNo);
}
