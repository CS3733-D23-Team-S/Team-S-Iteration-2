package edu.wpi.teamname.controllers.servicerequests.flowerdelivery;

import edu.wpi.teamname.DAOs.DataBaseRepository;
import edu.wpi.teamname.Main;
import edu.wpi.teamname.ServiceRequests.flowers.Flower;
import edu.wpi.teamname.controllers.NewHomeController;
import edu.wpi.teamname.controllers.PopUpController;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.awt.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class FlowerPopupController extends PopUpController {
  @FXML MFXButton addcartbutton;
  @FXML MFXButton decrementbutton;
  @FXML ImageView flowerimage;
  @FXML MFXButton incrementbutton;
  @FXML Label quantitylabel;
  @FXML Label flowername;
  @FXML Text flowerdescription;

  @FXML Text PriceText;
  @FXML Text SizeText;

  @FXML private DataBaseRepository dbr = DataBaseRepository.getInstance();
  // public static Cart flowerCart = new Cart(1);
  public static String recipient;

  public void initialize() {
    showInfo();
    addcartbutton.setOnMouseClicked(event -> createDelivery());
    flowercounter = 1;
    incrementbutton.setOnMouseClicked(event -> addquantity());
    decrementbutton.setOnMouseClicked(event -> subtractquantity());
  }

  private void showInfo() {
    flowername.setText(dbr.getFlowerDAO().get(FlowerDeliveryController.flowerID).getName());
    flowerdescription.setText(
        dbr.getFlowerDAO().get(FlowerDeliveryController.flowerID).getDescription());

    PriceText.setText(
        String.valueOf(dbr.getFlowerDAO().get(FlowerDeliveryController.flowerID).getPrice()));
    SizeText.setText(dbr.getFlowerDAO().get(FlowerDeliveryController.flowerID).getSize().name());

    Image image =
        new Image(
            Main.class
                .getResource(dbr.getFlowerDAO().get(FlowerDeliveryController.flowerID).getImage())
                .toString());
    flowerimage.setImage(image);
  }

  private void createDelivery() {
    System.out.println("added");
    Flower flower = dbr.getFlowerDAO().get(FlowerDeliveryController.flowerID);
    flower.setQuantity(flowercounter);
    NewHomeController.flowerCart.getCartItems().add(flower);
    stage.close();
  }

  public static int flowercounter;

  public void addquantity() {
    flowercounter++;
    quantitylabel.setText(Integer.toString(flowercounter));
  }

  public void subtractquantity() {
    flowercounter--;
    quantitylabel.setText(Integer.toString(flowercounter));
  }
}
