package bank_management_system;


import common.StageFactory;
import home_screen.HomeScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class ApplicationMain extends Application {
	
	public static void main(String args[]) {
		launch(args);
	}
	@Override
	public void start(Stage stage) {

		StageFactory.stage = stage;
		new HomeScreen().show();
	}
}