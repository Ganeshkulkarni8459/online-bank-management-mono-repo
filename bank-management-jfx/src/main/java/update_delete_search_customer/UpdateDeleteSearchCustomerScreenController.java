package update_delete_search_customer;

import java.io.IOException;

import admin_management.AdminManagementScreen;
import common.RestUtil;
import dto.AdminCanDeleteTheCustomersResponse;
import dto.AdminCanEditTheCustomersRequest;
import dto.AdminCanEditTheCustomersResponse;
import dto.AdminCanSearchTheCustomersResponse;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

public class UpdateDeleteSearchCustomerScreenController {
	@FXML
    private TextField customerId;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField mobileNumber;

    @FXML
    private TextField email;

    @FXML
    private TextField presentAddress;

    @FXML
    private TextField password;
    
    @FXML
    private TextField accountStatus;

    @FXML
    private Button searchUser;

    @FXML
    private Button editUser;

    @FXML
    private Button delete;

    @FXML
    private Button back;
    
    @FXML
    private Label label;

    @FXML
    private void searchUser() throws InterruptedException {
    	try {
			String searchUrl = "http://localhost:8081/api/v1/admin/search/customer/" + customerId.getText();
			
			AdminCanSearchTheCustomersResponse response = RestUtil.sendGetRequest(searchUrl, AdminCanSearchTheCustomersResponse.class);

			if (response.getStatus().equals("Fail")) {
				showAlert("Customer Not Found", AlertType.WARNING);
				 updateStatusLabel("Customer Not Found", "RED", 20);
			} else {
				firstName.setText(response.getFirstName());
				lastName.setText(response.getLastName());
				mobileNumber.setText(response.getMobileNumber());
				email.setText(response.getEmailId());
				presentAddress.setText(response.getPresentAddress());
				password.setText(response.getPassword());
				accountStatus.setText(response.getAccountStatus());

				updateStatusLabel("Customer Found Successfuly", "GREEN", 20);
                
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    private void editUser() throws InterruptedException {
    	
    	CustomerResgistration table = new CustomerResgistration();
    	
    	table.setFirstName(firstName.getText());
    	table.setLastName(lastName.getText());
    	table.setMobileNo(mobileNumber.getText());
    	table.setEmailId(email.getText());
    	table.setPresentAddress(presentAddress.getText());
    	table.setPassword(password.getText());
    	table.setAccountStatus(accountStatus.getText());
    
 
    	try {
            System.out.println(customerId.getText());
            String updateUrl = "http://localhost:8081/auth/v1/admin/update/customer/" + customerId.getText();

            AdminCanEditTheCustomersResponse response = RestUtil.sendPostRequest(updateUrl, AdminCanEditTheCustomersResponse.class, table);

            if (response.getStatus().equals("Fail")) {
            	showAlert("Customer Update Failed: " + response.getMessage(), AlertType.ERROR);
                updateStatusLabel("Customer Update Failed: " + response.getMessage(), "RED", 20);
            } else {
            	showAlert("Customer Updated Successfully", AlertType.INFORMATION);
                updateStatusLabel("Customer Updated Successfully", "GREEN", 20);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
  
    }
    
    @FXML
    private void delete() {
        try {
            String deleteUrl = "http://localhost:8081/api/v1/admin/delete/customer/" + customerId.getText();

            AdminCanDeleteTheCustomersResponse status = RestUtil.sendDeleteRequest(deleteUrl, AdminCanDeleteTheCustomersResponse.class);

            if (status.getStatus().equals("Success")) {
                showAlert("User Deleted Successfully", AlertType.INFORMATION);
            } else {
                showAlert("User Not Found or Deletion Failed", AlertType.WARNING);
            }
        } catch (IOException | InterruptedException e) {
            showAlert("Error: Failed to delete user - " + e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    private void back() {
    	new AdminManagementScreen().show();
    }
    
    private void showAlert(String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void updateStatusLabel(String message, String color, int fontSize) {
    	label.setText(message);
    	label.setTextFill(Paint.valueOf(color));
    	label.setStyle("-fx-font-size: " + fontSize + ";");
    }
	
}



