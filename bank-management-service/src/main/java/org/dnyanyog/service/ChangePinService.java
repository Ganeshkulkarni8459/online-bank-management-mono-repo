package org.dnyanyog.service;

import java.util.Optional;

import org.dnyanyog.dto.request.ChangePinRequest;
import org.dnyanyog.dto.response.ChangePinResponse;
import org.dnyanyog.entity.Account;
import org.dnyanyog.repo.ChangePinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePinService {

	@Autowired
	ChangePinRepository repo;

	@Autowired
	ChangePinResponse response;

	public ChangePinResponse changePin(ChangePinRequest changePin) {
		Optional<Account> receivedData = repo.findByCardNoAndAtmPin(changePin.getCardNumber(), changePin.getCurrPin());

		System.out.println(changePin.getCurrPin());
		System.out.println(changePin.getCardNumber());

		if (receivedData.isPresent()) {
			Account acc = receivedData.get();
			
			if(acc.getAccountStatus().equals("Open")) {
				
				if (acc.getAtmPin().trim().equals(changePin.getCurrPin().trim())) {
					acc.setAtmPin(changePin.getNewPin());
					repo.save(acc);

					response.setStatus("Success");
					response.setMessage("PIN Updated Successfully");
				} else {
					response.setStatus("Error");
					response.setMessage("Incorrect Current PIN");
				}

			} else {
				response.setStatus("Fail");
				response.setMessage("Your Account Has Been Locked Please Contact The Admin");
				
			}
			}else {
				response.setStatus("Error");
				response.setMessage("Account not found");
			}
		return response;

	}
}