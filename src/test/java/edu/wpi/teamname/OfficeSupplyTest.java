package edu.wpi.teamname;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.wpi.teamname.ServiceRequests.OfficeSupplies.*;
import org.junit.jupiter.api.Test;

public class OfficeSupplyTest {
  OfficeSupplyDAOImpl osDAOI = new OfficeSupplyDAOImpl();
  OfficeSupplyDeliveryDAOImpl OSDAOI = new OfficeSupplyDeliveryDAOImpl();
  OfficeSupply OfficeSupply1;
  OfficeSupplyDelivery FD;

  @Test
  public void testNewOfficeSupply() {
    OfficeSupply1 = new OfficeSupply(1000, "test OfficeSupply", 10.0, "test", 0, false, "image");

    osDAOI.add(OfficeSupply1);

    assertEquals(osDAOI.get(1000), OfficeSupply1);
  }

  @Test
  public void testOfficeSupplyDelivery() {
    FD =
        new OfficeSupplyDelivery(
            1000, "acarrt", null, null, "a room", "me", "ur mom", "Complete", 10.01, "n");

    OSDAOI.add(FD);

    assertEquals(FD, OSDAOI.get(1000));
  }

  @Test
  public void testCart() {
    OfficeSupplyCart cart = new OfficeSupplyCart(1000);
    OfficeSupply OfficeSupply2 =
        new OfficeSupply(1001, "OfficeSupply2", 20.0, "waaaaaa", 2, false, "image");
    OfficeSupply1 = new OfficeSupply(1000, "test OfficeSupply", 10.0, "Hello", 3, false, "image");
    cart.addOfficeSupplyItem(OfficeSupply1);
    cart.addOfficeSupplyItem(OfficeSupply2);

    assertEquals(cart.getTotalPrice(), 70);
  }
}
