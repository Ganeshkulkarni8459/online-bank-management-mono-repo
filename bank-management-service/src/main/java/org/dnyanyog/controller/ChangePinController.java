package org.dnyanyog.controller;


import org.dnyanyog.dto.request.ChangePinRequest;
import org.dnyanyog.dto.response.ChangePinResponse;
import org.dnyanyog.service.ChangePinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChangePinController {

	@Autowired
	ChangePinService service;
	
	@PutMapping(path="/auth/user/change/pin")
	public ChangePinResponse changePin(@RequestBody ChangePinRequest changePin) {
		
		return service.changePin(changePin);
	}
}
