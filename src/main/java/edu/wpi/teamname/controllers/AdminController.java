package edu.wpi.teamname.controllers;

import edu.wpi.teamname.Main;
import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import net.kurobako.gesturefx.GesturePane;

public class AdminController {
  //  @FXML ImageView homeIcon;

  @FXML MFXButton mapEditorButton;
  @FXML MFXButton mealRequestsButton;
  @FXML MFXButton roomRequestsButton;
  @FXML MFXButton flowerRequestsButton;

  @FXML TableView<MealRequest> toDoTable;
  public ObservableList<MealRequest> data = FXCollections.observableArrayList();
  @FXML
  TableColumn<MealRequest, String> deliveryID1 = new TableColumn<>("Service Request Type");
  @FXML TableColumn<MealRequest, String> itemsOrdered1 = new TableColumn<>("Items Ordered");
  @FXML TableColumn<MealRequest, String> timeOrdered1 = new TableColumn<>("Time Ordered");
  @FXML TableColumn<MealRequest, String> orderer1 = new TableColumn<>("Orderer");
  @FXML TableColumn<MealRequest, String> status1 = new TableColumn<>("Status");


  @FXML
  public void initialize() {

    mapEditorButton.setOnMouseClicked(event -> goToMapEditorPage());
    mealRequestsButton.setOnMouseClicked(event -> goToMealRequestsSubmittedPage());
    roomRequestsButton.setOnMouseClicked(event -> goToRoomRequestsSubmittedPage());
    flowerRequestsButton.setOnMouseClicked(event -> goToFlowerRequestsSubmittedPage());
  }


  public void goToMapEditorPage() {
    Navigation.navigate(Screen.BETTER_MAP_EDITOR);
  }

  public void goToMealRequestsSubmittedPage() {
    Navigation.navigate(Screen.SUBMITTED_MEAL_REQUESTS);
  }

  public void goToRoomRequestsSubmittedPage() {
    Navigation.navigate(Screen.SUBMITTED_ROOM_REQUESTS);
  }

  public void goToFlowerRequestsSubmittedPage() {
    Navigation.navigate(Screen.FLOWER_REQTABLE);
  }
}
