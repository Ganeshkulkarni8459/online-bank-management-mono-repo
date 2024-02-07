package org.dnyanyog.repo;

import java.util.Optional;

import org.dnyanyog.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface ChangePinRepository extends JpaRepository<Account,Long> {
	
	Optional<Account> findByCardNoAndAtmPin(String cardNumber,String currPin);
	
	//Optional<Account> findByCardNoAndAtmPin(String cardNo,String atmPin);


}
