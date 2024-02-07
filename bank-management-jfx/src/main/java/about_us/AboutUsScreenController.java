package about_us;

import home_screen.HomeScreen;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AboutUsScreenController {

    @FXML
    private Button back;

 
    @FXML
    private void back() {
       new HomeScreen().show();
    }

  
}
