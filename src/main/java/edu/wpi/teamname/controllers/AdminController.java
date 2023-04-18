package edu.wpi.teamname.controllers;

import edu.wpi.teamname.Main;
import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.util.ArrayList;
import java.util.LinkedList;
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
import lombok.Getter;
import net.kurobako.gesturefx.GesturePane;

class MealRequest1 {
  @Getter public String mealID1;

  @Getter public String itemsOrdered1;
  @Getter public String timeOrdered1;
  @Getter public String orderer1;
  @Getter public String status1;

  MealRequest1(
          String mealID1,
          String orderer1,
          String itemsOrdered1,
          String timeOrdered1,
          String status1) {
    this.mealID1 = mealID1;
    this.orderer1 = orderer1 ;
    this.itemsOrdered1 = itemsOrdered1;
    this.timeOrdered1 = timeOrdered1;
    this.status1 = status1;
  }


}

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

    List<MealRequest> ToDo = new LinkedList<>();
    ToDo.add(new MealRequest("Meal", "17.3.2023", "Complete", " ",));
    ToDo.add(new MealRequest("Room", "17.3.2023", "Complete", " ",));
    ToDo.add(new MealRequest("Flower", "17.3.2023", "Complete", " ",));

//    timeOrdered1.setCellValueFactory(
//            (row) -> new SimpleStringProperty(row.getValue().get()));
//    timeOrdered.setCellValueFactory((row) -> new SimpleStringProperty(row.getValue().timeOrdered));
//    status.setCellValueFactory((row) -> new SimpleStringProperty(row.getValue().getStatus()));
//
//    status.setCellValueFactory(new PropertyValueFactory<>("status"));
//    status.setCellFactory(
//            column -> {
//              return new TableCell<toDo, String>() {
//                private final ComboBox<String> dropdown = new ComboBox<>();
//
//                {
//                  dropdown.getItems().addAll("Recieved", "On the way!", "Yet to start");
//                  dropdown.setOnAction(
//                          event -> {
//                            toDo item = getTableView().getItems().get(getIndex());
//                            item.setCategory(dropdown.getSelectionModel().getSelectedItem());
//                            System.out.println(
//                                    "Selected:" + dropdown.getSelectionModel().getSelectedItem());
//                          });
//                }
//
//                @Override
//                protected void updateItem(String item, boolean empty) {
//                  super.updateItem(item, empty);
//                  if (empty) {
//                    setGraphic(null);
//                  } else {
//                    dropdown.getSelectionModel().select(item);
//                    setGraphic(dropdown);
//                  }
//                }
//              };
//            });
//
//    final ObservableList<toDo> observableMealList = FXCollections.observableList(ToDo);
//    // mealRequestsTable.setItems(observableMealList);
//    toDoTable.getItems().addAll(observableMealList)

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
