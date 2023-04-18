package edu.wpi.teamname;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.wpi.teamname.ServiceRequests.OfficeSupplies.*;
import java.sql.Date;
import java.sql.Time;
import org.junit.jupiter.api.Test;

public class OfficeSupplyTest {
  OfficeSupplyDAOImpl fDAOI = new OfficeSupplyDAOImpl();
  OfficeSupplyDeliveryDAOImpl FDDAOI = new OfficeSupplyDeliveryDAOImpl();
  OfficeSupply officeSupply1;
  OfficeSupplyDelivery OSD;

  @Test
  public void testNewFlower() {
    officeSupply1 = new OfficeSupply(1000, "test os", 10.0, "Hello", 3, false, "image");

    fDAOI.add(officeSupply1);

    assertEquals(fDAOI.get(1000), officeSupply1);
  }

  Time t = new Time(12, 12, 12);
  Date d = new Date(1999, 12, 12);

  @Test
  public void testFlowerDelivery() {
    OSD =
        new OfficeSupplyDelivery(
            1000, "acarrt", d, t, "a room", "me", "ur mom", "Complete", 10.01, "n");

    FDDAOI.add(OSD);

    assertEquals(OSD, FDDAOI.get(1000));
  }

  @Test
  public void testCart() {
    OfficeSupplyCart cart = new OfficeSupplyCart(1000);
    OfficeSupply flower2 = new OfficeSupply(1001, "flower2", 20.0, "waaaaaa", 3, false, "image");
    officeSupply1 = new OfficeSupply(1000, "test flower", 10.0, "Hello", 1, false, "image");
    cart.addOfficeSupplyItem(officeSupply1);
    cart.addOfficeSupplyItem(flower2);

    assertEquals(cart.getTotalPrice(), 70);
  }
}
