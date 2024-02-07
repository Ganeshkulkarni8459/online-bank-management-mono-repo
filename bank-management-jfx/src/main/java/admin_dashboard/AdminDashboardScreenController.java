package admin_dashboard;

import admin_management.AdminManagementScreen;
import home_screen.HomeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import total_customer.TotalCustomers;
import total_customer_bank_account.TotalCustomerAccount;
import total_customer_bank_transactions.TotalCustomerBankTransaction;

public class AdminDashboardScreenController {

	@FXML
	private Button totalCustomer;

	@FXML
	private Button totalAccounts;

	@FXML
	private Button totalTransaction;

	@FXML
	private Button back;

	public void totalCustomer(ActionEvent event) {
		new TotalCustomers().show();
	}
	public void totalAccounts(ActionEvent event) {
		new TotalCustomerAccount().show();
	}

	public void totalTransaction(ActionEvent event) {
		new TotalCustomerBankTransaction().show();
	}

	public void back(ActionEvent event) {
		new AdminManagementScreen().show();
	}
}
