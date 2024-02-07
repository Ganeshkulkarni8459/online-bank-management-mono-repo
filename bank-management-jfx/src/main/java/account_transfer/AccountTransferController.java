package account_transfer;

import common.RestUtil;
import customer_management.CustomerManagementScreen;
import dto.CustomerTranferAmountRequest;
import dto.CustomerTransferAmountResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AccountTransferController {

    @FXML
    private TextField fromAccountNumber;

    @FXML
    private TextField toAccountNumber;

    @FXML
    private TextField amount;

    @FXML
    private TextField pin;

    @FXML
    private Button accountTransfer;

    @FXML
    private Button back;

    @FXML
    private Label messageLabel;

    public void accountTransfer(ActionEvent event) {
        CustomerTranferAmountRequest transferRequest = new CustomerTranferAmountRequest();
        transferRequest.setFromcardNo(fromAccountNumber.getText());
        transferRequest.setTocardNo(toAccountNumber.getText());
        transferRequest.setBalance(Double.parseDouble(amount.getText()));
        transferRequest.setAtmPin(pin.getText());

        try {
            CustomerTransferAmountResponse transferResponse = RestUtil.sendPostRequest(
                    "http://localhost:8081/api/v1/customer/transfer/balance", CustomerTransferAmountResponse.class,
                    transferRequest);

            if ("Success".equalsIgnoreCase(transferResponse.getStatus())) {
                showAlert("Success", "Amount Transferred Successfully");
            } else if ("Fail".equalsIgnoreCase(transferResponse.getStatus())) {
                showAlert("Error", transferResponse.getMessage());

                if ("Your Account Has Been Locked. Please Contact The Admin".equalsIgnoreCase(transferResponse.getMessage())) {
                    showAlert("Account Locked", "Your account has been locked. Please contact the admin.");
                }
            }

            messageLabel.setText(transferResponse.getStatus() + ": " + transferResponse.getMessage());

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

    public void back(ActionEvent event) {
        new CustomerManagementScreen().show();
    }
}
