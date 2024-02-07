package add_account;

import admin_management.AdminManagementScreen;
import common.RestUtil;
import dto.AddAccountRequest;
import dto.AddAccountResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddAccountScreenController {

	@FXML
	private TextField customerId;

	@FXML
	private TextField cardNumber;

	@FXML
	private ComboBox<String> accountType;

	@FXML
	private TextField balance;

	@FXML
	private TextField atmPin;

	@FXML
	private ComboBox<String> accountStatus;

	@FXML
	private Button addAccount;

	@FXML
	private Button back;
	
	 public void initialize() {
	        // Initialize combo box options
	        accountType.getItems().addAll("Saving", "Current");
	        accountStatus.getItems().addAll("Open", "Lock");
	 }

	 public void addAccount(ActionEvent event) {
	        // Create AddAccountRequest object from input fields
	        AddAccountRequest addAccountRequest = new AddAccountRequest();
	        addAccountRequest.setCustomerId(customerId.getText());
	        addAccountRequest.setCardNo(cardNumber.getText());
	        addAccountRequest.setAccountType(accountType.getValue());
	        addAccountRequest.setAtmPin(atmPin.getText());
	        addAccountRequest.setBalance(balance.getText());
	        addAccountRequest.setAccountStatus(accountStatus.getValue());

	        try {
	            AddAccountResponse addAccountResponse = RestUtil.sendPostRequest(
	                    "http://localhost:8081/api/v1/admin/add/Account/"+customerId.getText(),
	                    AddAccountResponse.class,
	                    addAccountRequest
	            );

	            if ("Success".equals(addAccountResponse.getStatus())) {
	                showAlert("Success", "Account added successfully for Id  :"+customerId.getText());
	            } else {
	                showAlert("Error", "Account addition failed: " + addAccountResponse.getMessage());
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            showAlert("Error", "Something went wrong. Please try again.");
	        }
	    }

	    public void back(ActionEvent event) {
	        new AdminManagementScreen().show();
	    }
	    public void accountStatus(ActionEvent event) {
	        
	    }
	    public void accountType(ActionEvent event) {
	      
	    }

	    private void showAlert(String title, String content) {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }
}

