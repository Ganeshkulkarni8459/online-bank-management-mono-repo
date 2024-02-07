package customer_register;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.type.TypeReference;

import admin_management.AdminManagementScreen;
import common.RestUtil;
import dto.BranchAddResponse;
import dto.CustomerResgisterByAdminRequest;
import dto.CustomerResgisterByAdminResponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class CustomerRegisterController {

	@FXML
	private TextField firstName;

	@FXML
	private TextField lastName;
	
	@FXML
	private DatePicker dateOfBirth;

	@FXML
	private TextField mobileNumber;

	@FXML
	private ComboBox<String> gender;

	@FXML
	private ComboBox<String> selectBranch;

	@FXML
	private TextField password;

	@FXML
	private TextField permanentAddress;

	@FXML
	private TextField presentAddress;
	
	@FXML
	private TextField email;

	@FXML
	private Button register;

	@FXML
	private Button back;

	public void initialize() {
		// Initialize gender options
		ObservableList<String> genderOptions = FXCollections.observableArrayList("Male", "Female", "Others");
		gender.setItems(genderOptions);

		// Fetch and initialize branch options
		fetchBranchOptions();

		
		dateOfBirth.setDayCellFactory(getDayCellFactory());
	}
	private Callback<DatePicker, DateCell> getDayCellFactory() {
        return picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) > 0);
            }
        };
    }

	private void fetchBranchOptions() {
	    try {
	        // Make HTTP GET request to fetch branch options
	        TypeReference<List<String>> typeReference = new TypeReference<List<String>>() {};
	        List<String> branchNames = RestUtil.sendGetRequests(
	                "http://localhost:8081/api/admin/get/branchNames",
	                typeReference);

	        if (branchNames != null && !branchNames.isEmpty()) {
	            ObservableList<String> branchOptions = FXCollections.observableArrayList(branchNames);
	            selectBranch.setItems(branchOptions);
	        } else {
	            showAlert("Error", "No branch options available.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        showAlert("Error", "Something went wrong. Please try again.");
	    }
	}


	public void register(ActionEvent event) {
	    String firstNameText = firstName.getText().trim();
	    String lastNameText = lastName.getText().trim();
	    LocalDate dateOfBirthValue = dateOfBirth.getValue();
	    String emailText = email.getText().trim();
	    String mobileNumberText = mobileNumber.getText().trim();
	    String genderValue = gender.getValue();
	    String branchValue = selectBranch.getValue();
	    String passwordText = password.getText().trim();
	    String permanentAddressText = permanentAddress.getText().trim();
	    String presentAddressText = presentAddress.getText().trim();

	    if (firstNameText.isEmpty() || lastNameText.isEmpty() || dateOfBirthValue == null ||
	            emailText.isEmpty() || mobileNumberText.isEmpty() || genderValue == null ||
	            branchValue == null || passwordText.isEmpty() || permanentAddressText.isEmpty() ||
	            presentAddressText.isEmpty()) {
	        showAlert("Error", "Please fill in all the required fields.");
	        return; 
	    }

	    CustomerResgisterByAdminRequest request = new CustomerResgisterByAdminRequest();
	    request.setFirstName(firstNameText);
	    request.setLastName(lastNameText);
	    request.setDateOfBirth(dateOfBirthValue.toString());
	    request.setEmailId(emailText);
	    request.setMobileNo(mobileNumberText);
	    request.setGender(genderValue);
	    request.setBranch(branchValue);
	    request.setPasssword(passwordText);
	    request.setPermanentAddress(permanentAddressText);
	    request.setPresentAddress(presentAddressText);

	    try {
	        CustomerResgisterByAdminResponse response = RestUtil.sendPostRequest(
	                "http://localhost:8081/api/admin/add/customerbyadmin",
	                CustomerResgisterByAdminResponse.class,
	                request
	        );

	        if ("Success".equals(response.getStatus())) {
	            showAlert("Success", "Customer registered successfully");
	            showAlert("Success", "Customer Id for register Customer is as :" + response.getData().getCustomerId());
	        } else {
	            showAlert("Error", "Customer registration failed: " + response.getMessage());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        showAlert("Error", "Something went wrong. Please try again.");
	    }
	}

	public void back(ActionEvent event) {
		new AdminManagementScreen().show();
	}
	private void showAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
