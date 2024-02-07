package deposit_amount;

import common.RestUtil;
import customer_management.CustomerManagementScreen;
import dto.CustomerDepositAmountRequest;
import dto.CustomerDepositAmountResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DepositController {

    @FXML
    private TextField amount;

    @FXML
    private TextField cardNumber;

    @FXML
    private TextField pin;

    @FXML
    private Button deposit;

    @FXML
    private Button back;

    public void deposit(ActionEvent event) {
        if (validateInputs()) {
            CustomerDepositAmountResponse depositResponse = performDeposit();

            if ("Success".equals(depositResponse.getStatus())) {
                showAlert("Success", "Deposit successful!");
            } else if ("Fail".equals(depositResponse.getStatus())) {
                showAlert("Error", depositResponse.getResponse());
                
                if ("Your Account Has Temporary Lock, Please Contact The Admin".equals(depositResponse.getResponse())) {
                    showAlert("Account Locked", "Your account has been locked. Please contact the admin.");
                }
            }
        }
    }

    public void back(ActionEvent event) {
        new CustomerManagementScreen().show();
    }

    private boolean validateInputs() {
        if (amount.getText().isEmpty() || pin.getText().isEmpty() || cardNumber.getText().isEmpty()) {
            showAlert("Error", "Please enter all amount, pin, and Card Number.");
            return false;
        }
        return true;
    }
    private CustomerDepositAmountResponse performDeposit() {
        CustomerDepositAmountResponse response = new CustomerDepositAmountResponse();

        String url = "http://localhost:8081/api/v1/customer/add/balance";
        CustomerDepositAmountRequest depositRequest = new CustomerDepositAmountRequest();
        depositRequest.setBalance(amount.getText());
        depositRequest.setAtmPin(pin.getText());
        depositRequest.setCardNo(cardNumber.getText());

        try {
            response = RestUtil.sendPostRequest(url, CustomerDepositAmountResponse.class, depositRequest);

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("Fail");
            response.setResponse("Something went wrong. Please try again.");
            return response;
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
