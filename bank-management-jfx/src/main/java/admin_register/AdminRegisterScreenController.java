package admin_register;

import common.RestUtil;
import dto.AdminResgisterRequest;
import dto.AdminResgisterResponse;
import home_screen.HomeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AdminRegisterScreenController {

    @FXML
    private TextField adminEmail;
    @FXML
    private TextField adminId;
    @FXML
    private TextField password;
    @FXML
    private Button registerAdmin;
    
    @FXML
    private Button back;

    public void registerAdmin(ActionEvent event) {
        String email = adminEmail.getText();
        String id = adminId.getText();
        String pwd = password.getText();

        if (email.isEmpty() || id.isEmpty() || pwd.isEmpty()) {
            showAlert("Error", "Please fill in all the fields.");
            return; 
        }
        if (!isValidEmail(email)) {
			showAlert("Error", "Please enter a valid email address.");
			return;
		}

//		if (!isValidAdminId(id)) {
//			showAlert("Error", "Admin ID must contain at least one small letter and one digit.");
//			return;
//		}

//		if (!isValidPassword(pwd)) {
//			showAlert("Error",
//					"Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, and one digit.");
//			return;
//		}

        AdminResgisterRequest adminRegisterRequest = new AdminResgisterRequest();
        adminRegisterRequest.setAdminEmail(email);
        adminRegisterRequest.setAdminId(id);
        adminRegisterRequest.setPassword(pwd);

        try {
            AdminResgisterResponse adminRegisterResponse = RestUtil.sendPostRequest(
                    "http://localhost:8081/api/admin/register",
                    AdminResgisterResponse.class,
                    adminRegisterRequest
            );
            if (adminRegisterResponse.getStatus().equalsIgnoreCase("Success")) {
                showAlert("Success", "Admin Registered Successfully");
            } else {
                showAlert("Error", "Admin Registration Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Something went wrong. Please try again.");
        }
    }
//    private boolean isValidPassword(String password) {
//		String regex = "^(?=.[a-z])(?=.[A-Z])(?=.*\\d).{8,}$";
//		return password.matches(regex);
//	}

	private boolean isValidEmail(String email) {
		String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
		return email.matches(emailRegex);
	}

//	private boolean isValidAdminId(String adminId) {
//		String adminIdRegex = "^(?=.[a-z])(?=.\\d).+$";
//		return adminId.matches(adminIdRegex);
//	}

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void back(ActionEvent event) {
        new HomeScreen().show();
    }
}





