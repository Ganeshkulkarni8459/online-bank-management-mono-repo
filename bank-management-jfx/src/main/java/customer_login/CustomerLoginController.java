package customer_login;

import common.RestUtil;
import customer_management.CustomerManagementScreen;
import dto.CustomerLoginRequest;
import dto.CustomerLoginResponse;
import home_screen.HomeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CustomerLoginController {
	@FXML
	private TextField email;
	
	@FXML
	private TextField password;
	
	@FXML
	private Button login;
	
	@FXML
	private Button back;
	
	@FXML
	public void login(ActionEvent event) {
	    String userEmail = email.getText();
	    String userPassword = password.getText();

	    if (userEmail.isEmpty() || userPassword.isEmpty()) {
	        showAlert("Error", "Please enter both email and password.");
	        return;
	    }

	    String url = "http://localhost:8081/api/v1/customer/login";
	    CustomerLoginRequest loginRequest = new CustomerLoginRequest();
	    loginRequest.setEmailId(userEmail);
	    loginRequest.setPassword(userPassword);

	    try {
	        CustomerLoginResponse loginResponse = RestUtil.sendPostRequest(url, CustomerLoginResponse.class, loginRequest);

	        if ("Success".equalsIgnoreCase(loginResponse.getStatus())) {
	            showAlert("Success", "Login successful");
	            new CustomerManagementScreen().show();
	        } else {
	            showAlert("Error", "Login failed: " + loginResponse.getMessage());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        showAlert("Error", "Something went wrong. Please try again.");
	    }
	}
	public void back(ActionEvent event) {
		new HomeScreen().show();
	}
	
	private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
