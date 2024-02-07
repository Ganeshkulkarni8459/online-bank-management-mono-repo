package admin_management;

import add_account.AddAccountScreen;
import add_branch.AddBranchScreen;
import admin_dashboard.AdminDashboardScreen;
import admin_login.AdminLogin;
import customer_register.CustomerRegister;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import update_delete_search_customer.UpdateDeleteSearchCustomerScreen;

public class AdminManagementScreenController {
	
	@FXML
	private Button register;

	@FXML
	private Button addAccount;

	@FXML
	private Button dashBoard;

	@FXML
	private Button addBranch;

	@FXML
	private Button searchUpdateDelete;
	
	@FXML
	private Button logout;
	
	
	public void register(ActionEvent event) {
		new CustomerRegister().show();
	}
	public void addAccount(ActionEvent event) {
		new AddAccountScreen().show();
	}

	public void dashBoard(ActionEvent event) {
		new AdminDashboardScreen().show();
	}
	public void addBranch(ActionEvent event) {
		new AddBranchScreen().show();
	}
	public void searchUpdateDelete(ActionEvent event) {
		new UpdateDeleteSearchCustomerScreen().show();
	}
	public void logout(ActionEvent event) {
		new AdminLogin().show();
	}
}
