package change_pin;

import common.RestUtil;
import customer_management.CustomerManagementScreen;
import dto.AddAccountResponse;
import dto.ChangePinRequest;
import dto.ChangePinResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChangePinController {
	@FXML
	private TextField accountNumber;
	@FXML
	private TextField currPin;
	@FXML
	private TextField newPin;
	@FXML
	private TextField reEnterNewPin;
	@FXML
	private Button changePin;
	@FXML
	private Button back;

	@FXML
	private Label statusLabel;

	private void showAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}

	@FXML
	public void changePin(ActionEvent event) {
		String currentPin = currPin.getText();
		String newPinValue = newPin.getText();
		String reEnterNewPinValue = reEnterNewPin.getText();
		String accNumber = accountNumber.getText();

		if (currentPin.isEmpty() || newPinValue.isEmpty() || reEnterNewPinValue.isEmpty() || accNumber.isEmpty()) {
			showAlert("Error", "All fields must be filled");
			return;
		}
		if (!newPinValue.equals(reEnterNewPinValue)) {
			showAlert("Error", "New PINs do not match");
			return;
		}
		ChangePinRequest changePinRequest = new ChangePinRequest();
		changePinRequest.setCurrPin(currentPin);
		changePinRequest.setNewPin(newPinValue);
		changePinRequest.setCardNumber(accNumber);
		try {
			ChangePinResponse response = RestUtil.sendPutRequest("http://localhost:8081/auth/user/change/pin",
					ChangePinResponse.class, changePinRequest);

			if ("Success".equals(response.getStatus())) {
				showAlert("Success", "PIN Updated Successfully");
			} else if ("Fail".equals(response.getStatus())) {
				showAlert("Error", response.getMessage());
			} else {
				showAlert("Error", "Something went wrong. Please try again.");
			}
			if ("Fail".equals(response.getStatus())
					&& "Your Account Has Been Locked Please Contact The Admin".equals(response.getMessage())) {
				showAlert("Account Locked", "Your account has been locked. Please contact the admin.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			showAlert("Error", "Something went wrong. Please try again.");
		}
	}

	public void back(ActionEvent event) {
		new CustomerManagementScreen().show();
	}
}
