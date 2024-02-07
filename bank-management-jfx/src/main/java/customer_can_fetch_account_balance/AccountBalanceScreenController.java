package customer_can_fetch_account_balance;

import common.RestUtil;
import customer_management.CustomerManagementScreen;
import dto.GetTotalBalanceResponse;
import dto.GetTotalBalnaceRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AccountBalanceScreenController {

	@FXML
	private TextField balance;
	
	@FXML
	private TextField customerId;

	@FXML
	private Button back;
	
	@FXML
	private Button fetchBalance;

	@FXML
	private void fetchBalance() {
		String customerIdText = customerId.getText();

        if (customerIdText.isEmpty()) {
            showAlert("Error", "Customer ID cannot be empty.");
            return;
        }
        try {
            String url = "http://localhost:8081/api/v1/customer/get/balance/"+customerId.getText();
            GetTotalBalnaceRequest balanceRequest = new GetTotalBalnaceRequest();

            GetTotalBalanceResponse balanceResponse = RestUtil.sendGetRequest(url, GetTotalBalanceResponse.class);

            if ("Success".equals(balanceResponse.getStatus())) {
                balance.setText(String.valueOf(balanceResponse.getBalance()));
            } else {
                showAlert("Error", "Failed to get balance: " + balanceResponse.getMessage());
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid Customer ID. Please enter a valid number.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Something went wrong. Please try again.");
        }
	}
	
	@FXML
	private void back() {
		new CustomerManagementScreen().show();
	}
	private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}



