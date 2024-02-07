package add_branch;

import admin_management.AdminManagementScreen;
import common.RestUtil;
import dto.BranchAddRequest;
import dto.BranchAddResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddBranchScreenController {

	@FXML
	private TextField branchName;

	@FXML
	private TextField city;

	@FXML
	private TextField state;

	@FXML
	private Button addBranch;

	@FXML
	private Button back;

	public void addBranch(ActionEvent event) {
	    String branchNameText = branchName.getText().trim();
	    String cityText = city.getText().trim();
	    String stateText = state.getText().trim();

	    if (branchNameText.isEmpty() || cityText.isEmpty() || stateText.isEmpty()) {
	        showAlert("Error", "Please fill in all the fields.");
	        return; 
	    }

	    BranchAddRequest branchAddRequest = new BranchAddRequest();
	    branchAddRequest.setBranchName(branchNameText);
	    branchAddRequest.setBranchCity(cityText);
	    branchAddRequest.setBranchState(stateText);

	    try {
	        BranchAddResponse branchAddResponse = RestUtil.sendPostRequest(
	                "http://localhost:8081/api/admin/add/branch",
	                BranchAddResponse.class,
	                branchAddRequest
	        );

	        if ("Success".equals(branchAddResponse.getStatus())) {
	            showAlert("Success", "Branch added successfully");
	        } else {
	            showAlert("Error", "Branch addition failed: " + branchAddResponse.getMessage());
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
