package core.main;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

import java.util.Objects;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root =(AnchorPane)FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/login.fxml")));
			Scene scene = new Scene(root,600-10,362-10);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("application.css").toExternalForm());
			scene.setRoot(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
