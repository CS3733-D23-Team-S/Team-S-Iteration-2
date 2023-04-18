package edu.wpi.teamname.controllers;

import edu.wpi.teamname.DAOs.DataBaseRepository;
import edu.wpi.teamname.ServiceRequests.ConferenceRoom.RoomRequestDAO;
import edu.wpi.teamname.ServiceRequests.ConferenceRoom.Status;
import edu.wpi.teamname.ServiceRequests.FoodService.Food;
import edu.wpi.teamname.ServiceRequests.FoodService.FoodDelivery;
import edu.wpi.teamname.ServiceRequests.FoodService.FoodDeliveryDAOImp;
import edu.wpi.teamname.ServiceRequests.flowers.FlowerDelivery;
import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.sql.Time;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

// class MealRequest1 {
//  @Getter public String mealID1;
//
//  @Getter public String itemsOrdered1;
//  @Getter public String timeOrdered1;
//  @Getter public String orderer1;
//  @Getter public String status1;
//  private StringProperty category;

//  MealRequest1(
//          String mealID1,
//          String orderer1,
//          String itemsOrdered1,
//          String timeOrdered1,
//          String status1,
//          String category) {
//    this.mealID1 = mealID1;
//    this.orderer1 = orderer1 ;
//    this.itemsOrdered1 = itemsOrdered1;
//    this.timeOrdered1 = timeOrdered1;
//    this.status1 = status1;
//    this.category = new SimpleStringProperty(category);
//  }
//  public String getCategory() {
//    return category.get();
//  }
//
//  public void setCategory(String category) {
//    this.category.set(category);
//  }
//
//  public StringProperty categoryProperty() {
//    return category;
//  }
//
//
// }

public class AdminController {
  //  @FXML ImageView homeIcon;

  @FXML MFXButton mapEditorButton;
  @FXML MFXButton mealRequestsButton;
  @FXML MFXButton roomRequestsButton;
  @FXML MFXButton flowerRequestsButton;

  @FXML TableView mrt;

  @FXML TableView flowerRequestsTable;
  @FXML TableView roomRequestsTable;

  @FXML private DataBaseRepository dbr = DataBaseRepository.getInstance();

  // public ObservableList<MealRequest> data = FXCollections.observableArrayList();
  //  @FXML
  //  TableColumn<MealRequest1, String> mealID1 = new TableColumn<>("Service Request Type");
  //  @FXML TableColumn<MealRequest1, String> itemsOrdered1 = new TableColumn<>("Items Ordered");
  //  @FXML TableColumn<MealRequest1, String> timeOrdered1 = new TableColumn<>("Time Ordered");
  //  @FXML TableColumn<MealRequest1, String> orderer1 = new TableColumn<>("Orderer");
  //  @FXML TableColumn<MealRequest1, String> status1 = new TableColumn<>("Status");

  FoodDeliveryDAOImp foodDeliveryDAO = DataBaseRepository.getInstance().getFoodDeliveryDAO();
  RoomRequestDAO repo1 = DataBaseRepository.getInstance().getRoomRequestDAO();

  @FXML
  public void initialize() {
    mapEditorButton.setOnMouseClicked(event -> goToMapEditorPage());
    //    List<MealRequest1> ToDo = new LinkedList<>();
    //    ToDo.add(new MealRequest1("Meal", "Person1", "Rice", "time1",""," "));
    //    ToDo.add(new MealRequest1("Room", "Person2", "Octopus", "time2", "", ""));
    //    ToDo.add(new MealRequest1("Flower", "Person3", "Dodo", "time3","", ""));
    //
    //    timeOrdered1.setCellValueFactory(
    //            (row) -> new SimpleStringProperty(row.getValue().getTimeOrdered1()));
    //    status1.setCellValueFactory((row) -> new
    // SimpleStringProperty(row.getValue().getStatus1()));
    //    itemsOrdered1.setCellValueFactory((row) -> new
    // SimpleStringProperty(row.getValue().getItemsOrdered1()));
    //    orderer1.setCellValueFactory((row) -> new
    // SimpleStringProperty(row.getValue().getOrderer1()));
    //    mealID1.setCellValueFactory((row) -> new
    // SimpleStringProperty(row.getValue().getOrderer1()));

    TableColumn<Food, String> column1 = new TableColumn<>("RequestID");
    column1.setCellValueFactory(new PropertyValueFactory<>("deliveryID"));

    TableColumn<Food, String> column2 = new TableColumn<>("Cart");
    column2.setCellValueFactory(new PropertyValueFactory<>("cart"));

    TableColumn<Food, String> column3 = new TableColumn<>("Request Date");
    column3.setCellValueFactory(new PropertyValueFactory<>("date"));

    TableColumn<Food, String> column4 = new TableColumn<>("Request Time");
    column4.setCellValueFactory(new PropertyValueFactory<>("time"));

    TableColumn<Food, String> column5 = new TableColumn<>("Location");
    column5.setCellValueFactory(new PropertyValueFactory<>("location"));

    TableColumn<Food, String> column6 = new TableColumn<>("Orderer");
    column6.setCellValueFactory(new PropertyValueFactory<>("orderer"));

    TableColumn<Food, String> column7 = new TableColumn<>("Assigned To");
    column7.setCellValueFactory(new PropertyValueFactory<>("assignedTo"));

    TableColumn<Food, String> column8 = new TableColumn<>("Status");
    column8.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

    TableColumn<Food, String> column9 = new TableColumn<>("Cost");
    column9.setCellValueFactory(new PropertyValueFactory<>("cost"));

    TableColumn<Food, String> column10 = new TableColumn<>("Notes");
    column10.setCellValueFactory(new PropertyValueFactory<>("notes"));

    mrt.getColumns().add(column1);
    mrt.getColumns().add(column2);
    mrt.getColumns().add(column3);
    mrt.getColumns().add(column4);
    mrt.getColumns().add(column5);
    mrt.getColumns().add(column6);
    mrt.getColumns().add(column7);
    mrt.getColumns().add(column8);
    mrt.getColumns().add(column9);
    mrt.getColumns().add(column10);

    mrt.setItems(FXCollections.observableArrayList(foodDeliveryDAO.getAll()));

    for (FoodDelivery order : foodDeliveryDAO.getAll()) {
      mrt.getItems().add(order);
    }

    TableColumn<FlowerDelivery, Integer> column11 = new TableColumn<>("DeliveryID");
    column1.setCellValueFactory(new PropertyValueFactory<>("deliveryid"));

    TableColumn<FlowerDelivery, String> column12 = new TableColumn<>("Cart");
    column2.setCellValueFactory(new PropertyValueFactory<>("cart"));
    //
    TableColumn<FlowerDelivery, Date> column13 = new TableColumn<>("Order Date");
    column3.setCellValueFactory(new PropertyValueFactory<>("orderdate"));
    //
    TableColumn<FlowerDelivery, Time> column14 = new TableColumn<>("Order Time");
    column4.setCellValueFactory(new PropertyValueFactory<>("ordertime"));
    //
    TableColumn<FlowerDelivery, String> column15 = new TableColumn<>("Room");
    column5.setCellValueFactory(new PropertyValueFactory<>("room"));
    //
    TableColumn<FlowerDelivery, String> column16 = new TableColumn<>("Ordered By");
    column6.setCellValueFactory(new PropertyValueFactory<>("orderedby"));
    //
    TableColumn<FlowerDelivery, String> column17 = new TableColumn<>("Assigned Employee");
    column7.setCellValueFactory(new PropertyValueFactory<>("assignedto"));
    //
    TableColumn<FlowerDelivery, Status> column18 = new TableColumn<>("Order Status");
    column8.setCellValueFactory(new PropertyValueFactory<>("orderstatus"));
    //
    TableColumn<FlowerDelivery, Double> column19 = new TableColumn<>("Cost");
    column9.setCellValueFactory(new PropertyValueFactory<>("cost"));
    //
    flowerRequestsTable.getColumns().add(column11);
    flowerRequestsTable.getColumns().add(column12);
    flowerRequestsTable.getColumns().add(column13);
    flowerRequestsTable.getColumns().add(column14);
    flowerRequestsTable.getColumns().add(column15);
    flowerRequestsTable.getColumns().add(column16);
    flowerRequestsTable.getColumns().add(column17);
    flowerRequestsTable.getColumns().add(column18);
    flowerRequestsTable.getColumns().add(column19);
    //
    for (FlowerDelivery order : dbr.getFlowerDeliveryDAO().getAll()) {
      flowerRequestsTable.getItems().add(order);
    }
  }

  //    TableColumn<ConfRoomRequest, String> column21 = new TableColumn<>("Date Ordered");
  //      column21.setCellValueFactory(new PropertyValueFactory<>("dateOrdered"));
  //
  //    TableColumn<ConfRoomRequest, String> column22 = new TableColumn<>("Event Date");
  //      column22.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
  //
  //    TableColumn<ConfRoomRequest, String> column23 = new TableColumn<>("Start Time");
  //      column23.setCellValueFactory(new PropertyValueFactory<>("startTime"));
  //
  //    TableColumn<ConfRoomRequest, String> column24 = new TableColumn<>("End Time");
  //      column24.setCellValueFactory(new PropertyValueFactory<>("EndTime"));
  //
  //    TableColumn<ConfRoomRequest, String> column25 = new TableColumn<>("Room");
  //      column25.setCellValueFactory(new PropertyValueFactory<>("room"));
  //
  //    TableColumn<ConfRoomRequest, String> column26 = new TableColumn<>("Reserved By");
  //      column26.setCellValueFactory(new PropertyValueFactory<>("reservedBy"));
  //
  //    TableColumn<ConfRoomRequest, String> column27 = new TableColumn<>("Event Name");
  //      column27.setCellValueFactory(new PropertyValueFactory<>("eventName"));
  //
  //    TableColumn<ConfRoomRequest, String> column28 = new TableColumn<>("Event Description");
  //      column28.setCellValueFactory(new PropertyValueFactory<>("eventDescription"));
  //
  //    TableColumn<ConfRoomRequest, String> column29 = new TableColumn<>("Assigned To");
  //      column29.setCellValueFactory(new PropertyValueFactory<>("assignedTo"));
  //
  //    TableColumn<ConfRoomRequest, String> column30 = new TableColumn<>("Order Status");
  //      column30.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
  //
  //    TableColumn<ConfRoomRequest, String> column31 = new TableColumn<>("Notes");
  //      column31.setCellValueFactory(new PropertyValueFactory<>("notes"));
  //
  //      roomRequestsTable.getColumns().add(column21);
  //      submittedRoomRequestsTable.getColumns().add(column22);
  //      submittedRoomRequestsTable.getColumns().add(column23);
  //      submittedRoomRequestsTable.getColumns().add(column24);
  //      submittedRoomRequestsTable.getColumns().add(column25);
  //      submittedRoomRequestsTable.getColumns().add(column26);
  //      submittedRoomRequestsTable.getColumns().add(column27);
  //      submittedRoomRequestsTable.getColumns().add(column28);
  //      submittedRoomRequestsTable.getColumns().add(column29);
  //      submittedRoomRequestsTable.getColumns().add(column30);
  //      submittedRoomRequestsTable.getColumns().add(column31);
  //
  //      for (ConfRoomRequest req : repo1.getAll()) {
  //      roomRequestsTable.getItems().add(req);
  //    }

  //
  //  //
  //  //    orderStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
  //  //    orderStatus.setCellFactory(
  //  //  column ->
  //  //
  //  //  {
  //  //    return new TableCell<Food, String>() {
  //  //      private final ComboBox<String> dropdown = new ComboBox<>();
  //  //
  //  //      {
  //  //        dropdown.getItems().addAll("Recieved", "On the way!", "Yet to start");
  //  //        dropdown.setOnAction(
  //  //                event -> {
  //  //                  Food item = getTableView().getItems().get(getIndex());
  //  //                  item.setCategory(dropdown.getSelectionModel().getSelectedItem());
  //  //                  System.out.println(
  //  //                          "Selected:" + dropdown.getSelectionModel().getSelectedItem());
  //  //                });
  //  //      }
  //  //
  //  //      @Override
  //  //      protected void updateItem(String item, boolean empty) {
  //  //        super.updateItem(item, empty);
  //  //        if (empty) {
  //  //          setGraphic(null);
  //  //        } else {
  //  //          dropdown.getSelectionModel().select(item);
  //  //          setGraphic(dropdown);
  //  //        }
  //  //      }
  //  //    };
  //  //  });

  //    final ObservableList<Food> observableMealList = FXCollections.observableList(Food);
  //    // mealRequestsTable.setItems(observableMealList);
  //    mealRequestTable.getItems().addAll(observableMealList)

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
