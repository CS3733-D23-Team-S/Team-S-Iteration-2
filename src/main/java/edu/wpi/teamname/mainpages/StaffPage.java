package edu.wpi.teamname.mainpages;

import edu.wpi.teamname.App;
import edu.wpi.teamname.controllers.StaffController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StaffPage extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(App.class.getResource("views/StaffPage.fxml"));
    final BorderPane root = loader.load();
    StaffController staffController = new StaffController();
    loader.setController(staffController);

    final Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Staffpage");
    primaryStage.show();
  }
}
