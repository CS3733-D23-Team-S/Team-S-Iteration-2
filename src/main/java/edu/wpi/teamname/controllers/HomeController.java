package edu.wpi.teamname.controllers;

import static edu.wpi.teamname.navigation.Screen.CSV_MANAGE;
import static edu.wpi.teamname.navigation.Screen.PATHFINDING;

import edu.wpi.teamname.Main;
import edu.wpi.teamname.navigation.Navigation;
import edu.wpi.teamname.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HomeController {

  @FXML MFXButton mealDeliveryButton;
  @FXML MFXButton reserveRoomButton;

  @FXML MFXButton logoutButton;

  @FXML MenuButton app;
  @FXML Button buttonBarPathfinding;
  @FXML Button buttonBarReserveRoom;
  @FXML Button buttonBarMealDelivery;
  @FXML MFXButton exportButton;
  @FXML ImageView exitIcon;

  @FXML ImageView helpIcon;

  @FXML ImageView hospitalBg;
  @FXML MFXButton homeToPathfindingButton;

  public static int cartID = 1;

  @FXML
  public void initialize() {
    // Adding the menu option to exit application

    mealDeliveryButton.setOnMouseClicked(event -> goToMealPage());
    reserveRoomButton.setOnMouseClicked(event -> goToRoomPage());
    logoutButton.setOnMouseClicked(event -> goToLoginPage());
    helpIcon.setOnMouseClicked(event -> goToHelpPage());
    exitIcon.setOnMouseClicked(event -> exitApplication());
    homeToPathfindingButton.setOnMouseClicked(event -> Navigation.navigate(PATHFINDING));
    exportButton.setOnMouseClicked(event -> Navigation.navigate(CSV_MANAGE));

    // Menu bar button handlers
    buttonBarPathfinding.setOnMouseClicked(event -> Navigation.navigate(PATHFINDING));
    buttonBarMealDelivery.setOnMouseClicked(event -> goToMealPage());
    buttonBarReserveRoom.setOnMouseClicked(event -> goToRoomPage());

    Image hospitalBackground =
        new Image(
            Main.class
                .getResource("./HomepageImages/BrighamandWomensHospitalImage.jpeg")
                .toString());
    hospitalBg.setImage(hospitalBackground);

    Image helpbutton =
        new Image(
            Main.class
                .getResource("./HomepageImages/1200px-Icon-round-Question_mark.svg.png")
                .toString());
    helpIcon.setImage(helpbutton);

    buttonBarPathfinding.setOnMouseClicked(event -> Navigation.navigate(Screen.SERVICE_REQUEST));
    mealDeliveryButton.setOnMouseClicked(event -> Navigation.navigate(Screen.MEAL_DELIVERY1));
    //  mealdeliveryButton.setOnMouseClicked(event -> incrementCart());

    reserveRoomButton.setOnMouseClicked(event -> Navigation.navigate(Screen.ROOM_BOOKING));
  }

  public void incrementCart() {
    cartID++;
  }

  public void goToRoomPage() {
    Navigation.navigate(Screen.ROOM_BOOKING);
  }

  public void goToMealPage() {
    Navigation.navigate(Screen.MEAL_DELIVERY);
  }

  public void goToLoginPage() {
    Navigation.navigate(Screen.LOGIN_PAGE);
  }

  public void exitApplication() {
    Platform.exit();
  }

  public void goToHelpPage() {
    Navigation.navigate(Screen.HELP_PAGE);
  }
}
