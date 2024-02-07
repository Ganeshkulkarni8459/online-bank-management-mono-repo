package developer_contact;

import home_screen.HomeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeveloperContactScreenController {
	@FXML
    private Button back;

    @FXML
    public void back(ActionEvent event) {
        new HomeScreen().show();
    }
}
