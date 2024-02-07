package admin_login;

import admin_management.AdminManagementScreen;
import common.RestUtil;
import dto.AdminLoginRequest;
import dto.AdminLoginResponse;
import home_screen.HomeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import verification.VerificationScreen;

public class AdminLoginController {

	@FXML
	private TextField adminId;

	@FXML
	private TextField password;

	@FXML
	private Button loginAdmin;

	@FXML
	private Button back;
	
	@FXML
	private Button forgotPassword;

	@FXML
	public void loginAdmin(ActionEvent event) {
	    String url = "http://localhost:8081/api/admin/login"; 
	    AdminLoginRequest loginRequest = new AdminLoginRequest();
	    loginRequest.setAdminId(adminId.getText());
	    loginRequest.setPassword(password.getText());

	    try {
	        AdminLoginResponse loginResponse = RestUtil.sendPostRequest(url, AdminLoginResponse.class, loginRequest);

	        if ("Success".equals(loginResponse.getStatus())) {
	            showAlert("Success", "Login successful");
	            new AdminManagementScreen().show();
	        } else {
	            showAlert("Error", "Login failed: " + loginResponse.getMessage());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
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
	@FXML
	public void back(ActionEvent event) {
		new HomeScreen().show();
	}
	@FXML
	public void forgotPassword(ActionEvent event) {
		new VerificationScreen().show();
	}
}
