package edu.wpi.teamname.controllers;

import edu.wpi.teamname.Main;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lombok.Getter;
import net.kurobako.gesturefx.GesturePane;

class toDo {
  @Getter public String serviceRequestType;
  @Getter public String timeOrdered;
  @Getter public String status;
  private StringProperty category;
  // private ComboBox dropComboBox;

  toDo(String serviceRequestType, String timeOrdered, String status, String category) {
    this.serviceRequestType = serviceRequestType;
    this.timeOrdered = timeOrdered;
    this.status = status;
    this.category = new SimpleStringProperty(category);
    // this.dropComboBox = new ComboBox<>();
  }

  public String getCategory() {
    return category.get();
  }

  public void setCategory(String category) {
    this.category.set(category);
  }

  public StringProperty categoryProperty() {
    return category;
  }
}

public class StaffController {

  @FXML MFXButton floorL2Button;
  @FXML MFXButton floorL1Button;
  @FXML MFXButton floor1Button;
  @FXML MFXButton floor2Button;
  @FXML MFXButton floor3Button;
  ImageView floorView;
  @FXML GesturePane mapView;
  StackPane stackpane;
  AnchorPane anchorpane = new AnchorPane();

  Image floorL1 = new Image(String.valueOf(Main.class.getResource("images/00_thelowerlevel1.png")));

  Image floorL2 = new Image(String.valueOf(Main.class.getResource("images/00_thelowerlevel2.png")));

  Image floor1 = new Image(String.valueOf(Main.class.getResource("images/01_thefirstfloor.png")));

  Image floor2 = new Image(String.valueOf(Main.class.getResource("images/02_thesecondfloor.png")));

  Image floor3 = new Image(String.valueOf(Main.class.getResource("images/03_thethirdfloor.png")));

  @FXML TableView<toDo> toDoTable;
  public ObservableList<toDo> data = FXCollections.observableArrayList();
  @FXML TableColumn<toDo, String> serviceRequestType = new TableColumn<>("Service Request Type");
  @FXML TableColumn<toDo, String> timeOrdered = new TableColumn<>("Time Ordered");
  @FXML TableColumn<toDo, String> status = new TableColumn<>("Status");

  @FXML
  public void initialize() {

    List<toDo> ToDo = new LinkedList<>();
    ToDo.add(new toDo("Meal", "17.3.2023", "Complete", " "));
    ToDo.add(new toDo("Room", "17.3.2023", "Complete", " "));
    ToDo.add(new toDo("Flower", "17.3.2023", "Complete", " "));

    serviceRequestType.setCellValueFactory(
        (row) -> new SimpleStringProperty(row.getValue().getServiceRequestType()));
    timeOrdered.setCellValueFactory((row) -> new SimpleStringProperty(row.getValue().timeOrdered));
    status.setCellValueFactory((row) -> new SimpleStringProperty(row.getValue().getStatus()));

    //status.setCellValueFactory(new PropertyValueFactory<>("status"));
    status.setCellFactory(
        column -> {
          return new TableCell<toDo, String>() {
            private final ComboBox<String> dropdown = new ComboBox<>();

            {
              dropdown.getItems().addAll("Recieved", "On the way!", "Yet to start");
              dropdown.setOnAction(
                  event -> {
                    toDo item = getTableView().getItems().get(getIndex());
                    item.setCategory(dropdown.getSelectionModel().getSelectedItem());
                    System.out.println(
                        "Selected:" + dropdown.getSelectionModel().getSelectedItem());
                  });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
              super.updateItem(item, empty);
              if (empty) {
                setGraphic(null);
              } else {
                dropdown.getSelectionModel().select(item);
                setGraphic(dropdown);
              }
            }
          };
        });

    final ObservableList<toDo> observableMealList = FXCollections.observableList(ToDo);
    // mealRequestsTable.setItems(observableMealList);
    toDoTable.getItems().addAll(observableMealList);

    stackpane = new StackPane();
    floorView =
        new ImageView(
            new Image(String.valueOf(Main.class.getResource("images/00_thelowerlevel2.png"))));
    floorL2Button.setStyle("-fx-background-color: #1D2B94");
    stackpane.setPrefSize(800, 522);
    mapView.setContent(stackpane);
    // stackpane.setBackground(Background.fill(Color.RED));

    floorView.setImage(floor1);
    stackpane.getChildren().add(floorView);
    stackpane.getChildren().add(anchorpane);
    anchorpane.setBackground(Background.fill(Color.TRANSPARENT));

    floorL1Button.setOnMouseClicked(event -> switchToFloorL1());
    floorL2Button.setOnMouseClicked(event -> switchToFloorL2());
    floor1Button.setOnMouseClicked(event -> switchToFloor1());
    floor2Button.setOnMouseClicked(event -> switchToFloor2());
    floor3Button.setOnMouseClicked(event -> switchToFloor3());

    mapView.setMinScale(0.005);
    mapView.setScrollBarPolicy(GesturePane.ScrollBarPolicy.NEVER);
    Platform.runLater(
        new Runnable() {
          @Override
          public void run() {
            mapView.zoomTo(0.01, new Point2D(2500, 1750));
          }
        });
  }

  public void changeButtonColor() {
    if (floorView.getImage().equals(floorL1)) {
      floorL1Button.setStyle("-fx-background-color: #1D2B94");
      floorL2Button.setStyle("-fx-background-color: #CAD6F8");
      floor1Button.setStyle("-fx-background-color: #CAD6F8");
      floor2Button.setStyle("-fx-background-color: #CAD6F8");
      floor3Button.setStyle("-fx-background-color: #CAD6F8");

    } else if (floorView.getImage().equals(floorL2)) {
      floorL1Button.setStyle("-fx-background-color: #CAD6F8");
      floorL2Button.setStyle("-fx-background-color: #1D2B94");
      floor1Button.setStyle("-fx-background-color: #CAD6F8");
      floor2Button.setStyle("-fx-background-color: #CAD6F8");
      floor3Button.setStyle("-fx-background-color: #CAD6F8");

    } else if (floorView.getImage().equals(floor1)) {
      floorL1Button.setStyle("-fx-background-color: #CAD6F8");
      floorL2Button.setStyle("-fx-background-color: #CAD6F8");
      floor1Button.setStyle("-fx-background-color: #1D2B94");
      floor2Button.setStyle("-fx-background-color: #CAD6F8");
      floor3Button.setStyle("-fx-background-color: #CAD6F8");
    } else if (floorView.getImage().equals(floor2)) {
      floorL1Button.setStyle("-fx-background-color: #CAD6F8");
      floorL2Button.setStyle("-fx-background-color: #CAD6F8");
      floor1Button.setStyle("-fx-background-color: #CAD6F8");
      floor2Button.setStyle("-fx-background-color: #1D2B94");
      floor3Button.setStyle("-fx-background-color: #CAD6F8");
    } else if (floorView.getImage().equals(floor3)) {
      floorL1Button.setStyle("-fx-background-color: #CAD6F8");
      floorL2Button.setStyle("-fx-background-color: #CAD6F8");
      floor1Button.setStyle("-fx-background-color: #CAD6F8");
      floor2Button.setStyle("-fx-background-color: #CAD6F8");
      floor3Button.setStyle("-fx-background-color: #1D2B94");
    }
  }

  public void switchToFloorL1() {
    floorView.setImage(floorL1);
    changeButtonColor();
    // mapView.setContent(stackpane);
  }

  public void switchToFloorL2() {
    floorView.setImage(floorL2);
    changeButtonColor();
    //    stackpane.getChildren().remove(floorView);
    //    stackpane.getChildren().add(floorView);
    // mapView.setContent(stackpane);
  }

  public void switchToFloor1() {
    floorView.setImage(floor1);
    changeButtonColor();
    //    stackpane.getChildren().remove(floorView);
    //    stackpane.getChildren().add(floorView);
    //    mapView.setContent(stackpane);
  }

  public void switchToFloor2() {
    floorView.setImage(floor2);
    changeButtonColor();
    //    stackpane.getChildren().remove(floorView);
    //    stackpane.getChildren().add(floorView);
    //    mapView.setContent(stackpane);
  }

  public void switchToFloor3() {
    floorView.setImage(floor3);
    changeButtonColor();
    //    stackpane.getChildren().remove(floorView);
    //    stackpane.getChildren().add(floorView);
    //    mapView.setContent(stackpane);
  }

  List<Circle> floorL1Points = new ArrayList<>();
  List<Circle> floorL2Points = new ArrayList<>();
  List<Circle> floor1Points = new ArrayList<>();
  List<Circle> floor2Points = new ArrayList<>();
  List<Circle> floor3Points = new ArrayList<>();

  public void showFloorL1locations() {
    floorL1Points = new ArrayList<>();
    floorL2Points = new ArrayList<>();
    floor1Points = new ArrayList<>();
    floor2Points = new ArrayList<>();
    floor3Points = new ArrayList<>();
  }
}
