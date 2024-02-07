package customer_can_fetch_account_details;


import java.io.IOException;

import common.RestUtil;
import customer_management.CustomerManagementScreen;
import dto.GetCustomerInformationResponse;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AccountDetailsController {
	
	@FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField mobileNumber;

    @FXML
    private TextField emailId;

    @FXML
    private TextField branch;
    
    @FXML
    private TextField dateOFBirth;

    @FXML
    private TextField registerDate;

    @FXML
    private TextField accountNumber;

    @FXML
    private TextField pin;

    @FXML
    private TextField accountType;
    
    @FXML
    private TextField customerId;

    @FXML
    private Button close;
    
    @FXML
    private Button fetchDetails;
    
    @FXML
    private void close() {
    	new CustomerManagementScreen().show();
    }
    
    @FXML
    private void fetchDetails() throws IOException {
        try {
           // Long customerIdValue = Long.parseLong(customerId.getText()); // Assuming the customer id is entered as a number

            String apiUrl = "http://localhost:8081/api/v1/customer/get/information/" +customerId.getText() ;

            GetCustomerInformationResponse response = RestUtil.sendGetRequest(apiUrl, GetCustomerInformationResponse.class);

            if ("Success".equals(response.getStatus())) {
               
                firstName.setText(response.getFirstName());
                lastName.setText(response.getLastname());
                mobileNumber.setText(response.getMobileNo());
                emailId.setText(response.getEmailId());
                branch.setText(response.getBranch());
                accountNumber.setText(response.getCardNo());
                dateOFBirth.setText(response.getDateOfBirth());
                pin.setText(response.getAtmPin());
                accountType.setText(response.getAccountType());
                registerDate.setText(response.getRegisterDate());
                showAlert("Success", "Customer information retrieved successfully");
            } else {
                showAlert("Error", "Customer information retrieval failed: " + response.getMessage());
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid customer ID");
        } catch (IOException e) {
            showAlert("Error", "Error fetching customer information. Please try again.");
        } catch (Exception e) {
            showAlert("Error", "Something went wrong. Please try again.");
        }
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}


