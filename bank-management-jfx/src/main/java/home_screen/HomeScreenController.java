package home_screen;

import about_us.AboutUsScreen;
import admin_login.AdminLogin;
import admin_register.AdminRegisterScreen;
import customer_login.CustomerLogin;
import developer_contact.DeveloperContactScreen;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class HomeScreenController {

	private Button adminRegister;

	private Button adminLogin;

	private Button loginCustomer;

	private Button developerContact;

	private Button aboutUs;

	public void adminRegister(ActionEvent event) {
		new AdminRegisterScreen().show();
	}
	public void adminLogin(ActionEvent event) {
		new AdminLogin().show();
	}
	public void loginCustomer(ActionEvent event) {
		new CustomerLogin().show();
	}
	public void developerContact(ActionEvent event) {
		new DeveloperContactScreen().show();
	}
	public void aboutUs(ActionEvent event) {
		new AboutUsScreen().show();
	}

}
