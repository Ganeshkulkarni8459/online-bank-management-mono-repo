package org.dnyanyog.controller;

import org.dnyanyog.dto.response.GetCustomerInformationResponse;
import org.dnyanyog.service.GetCustomerInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetCustomerInformationController {

    @Autowired
    private GetCustomerInformationService getCustomerInformationService;

    @GetMapping(path = "/api/v1/customer/get/information/{customerId}")
    public ResponseEntity<GetCustomerInformationResponse> getCustomerInformation(@PathVariable(name = "customerId") Long customerId) {
        try {
            // Add validation if needed (e.g., customerId should not be null or negative)
            if (customerId == null || customerId < 0) {
                return ResponseEntity.badRequest().build(); // Return 400 Bad Request for invalid input
            }

            GetCustomerInformationResponse response = getCustomerInformationService.getCustomerInformation(customerId);

            if ("Success".equals(response.getStatus())) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); // Return 404 Not Found if customer not found
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return 500 Internal Server Error for unexpected issues
        }
    }
}
