package customer_management;

import account_transfer.AccountTransfer;
import change_pin.ChangePin;
import customer_can_fetch_account_balance.AccountBalanceScreen;
import customer_can_fetch_account_details.AccountDetails;
import customer_can_view_transaction_history.TransactionHistory;
import deposit_amount.Deposit;
import home_screen.HomeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import withdraw_amount.WithDrawAmount;

public class CustomerManagementScreenController {
	@FXML
	private Button accountBalance;

	@FXML
	private Button deposit;

	@FXML
	private Button accountTransfer;

	@FXML
	private Button withDrawCash;

	@FXML
	private Button transactionHistory;

	@FXML
	private Button changePin;

	@FXML
	private Button customerDetails;

	@FXML
	private Button logout;

	public void accountBalance(ActionEvent event) {
		
		new AccountBalanceScreen().show();
	}

	public void deposit(ActionEvent event) {
		new Deposit().show();
	}

	public void accountTransfer(ActionEvent event) {
		new AccountTransfer().show();
	}

	public void withDrawCash(ActionEvent event) {
		new WithDrawAmount().show();
	}

	public void transactionHistory(ActionEvent event) {
		new TransactionHistory().show();
	}

	public void changePin(ActionEvent event) {
		new ChangePin().show();
	}

	public void customerDetails(ActionEvent event) {
		new AccountDetails().show();
	}

	public void logout(ActionEvent event) {
		new HomeScreen().show();
	}

}
