package edu.wpi.teamname.controllers.servicerequests.conferenceroom;

import edu.wpi.teamname.ServiceRequests.ConferenceRoom.ConfRoomLocation;
import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;

public class RoomBookingDetailsController {

  @FXML MFXButton submitDetailsButton;
  @FXML MFXTextField eventTitleText;
  @FXML MFXTextField eventDescriptionText;
  @FXML MFXComboBox roomComboBox;
  @FXML MFXButton backButton;
  @FXML MFXButton clearButton;
  @FXML MFXDatePicker roomBookingDate;
  @FXML MFXTextField startTimeField;
  @FXML MFXTextField endTimeField;
  @FXML Pane startTimePane;

  @Setter @Getter String roomLocation;
  @Setter @Getter LocalDate eventDate;
  @Setter @Getter String startTime;
  @Setter @Getter String endTime;
  @Setter @Getter String eventTitle;
  @Setter @Getter String eventDescription;

  RoomBookingController rbc = new RoomBookingController();

  ArrayList<ConfRoomLocation> rbcRoomList = rbc.roomList;

  @FXML
  public void initialize() throws ParseException {
    submitDetailsButton.setOnMouseClicked(
        event -> Navigation.navigate(Screen.ROOM_BOOKING_CONFIRMATION));
    clearButton.setOnMouseClicked(event -> clearFields());

    initializeRoomComboBox();
  }

  // hard code items into room combo box (TODO read from list later)
  public void initializeRoomComboBox() {
    roomComboBox.getItems().add("BTM Conference Center");
    roomComboBox.getItems().add("Duncan Reid Conference Room");
    roomComboBox.getItems().add("Anesthesia Conf Floor L1");
    roomComboBox.getItems().add("Medical Records Conference Room Floor L1");
    roomComboBox.getItems().add("Abrams Conference Room");
    roomComboBox.getItems().add("Carrie M. Hall Conference Center Floor 2");
    roomComboBox.getItems().add("Shapiro Board Room MapNode 20 Floor 1");
  }

  // submit details from controller
  @FXML
  public void submitDetails(ActionEvent event) throws SQLException {
    roomLocation = roomComboBox.getValue().toString().replaceAll(" ", "");
    eventDate = roomBookingDate.getValue();
    startTime = startTimeField.getText();
    endTime = endTimeField.getText();
    eventTitle = eventTitleText.getText();
    eventDescription = eventDescriptionText.getText();
    if (roomComboBox.getValue().toString() == null
        || eventDate == null
        || startTime == null
        || endTime == null
        || eventTitle == null
        || eventDescription == null) {
      rbc.addNewRequest(roomLocation, eventDate, startTime, endTime, eventTitle, eventDescription);
    }
    // clearFields();
  }

  // clear text fields
  public void clearFields() {
    roomComboBox.setValue("");
    // startTimeText.clear();
    // endTimeText.clear();
    eventTitleText.clear();
    eventDescriptionText.clear();
  }
}
