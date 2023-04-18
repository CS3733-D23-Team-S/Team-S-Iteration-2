package edu.wpi.teamname;

import static edu.wpi.teamname.DAOs.orms.Floor.Floor3;
import static edu.wpi.teamname.DAOs.orms.NodeType.BATH;
import static org.junit.jupiter.api.Assertions.*;

import edu.wpi.teamname.DAOs.orms.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class MoveTest {
  Move move1 =
      new Move(
          new Node(100, 2265, 904, Floor3, "45 Francis"),
          new Location("Test", "Test", BATH),
          LocalDate.of(2023, 4, 5));

  @Test
  public void moveTest() {
    assertEquals(move1.getNodeID(), 100);
  }

  @Test
  public void toStringTest() {
    assertEquals(
        move1.toString(),
        "Move{nodeID = 100, location = Test, date = " + LocalDate.of(2023, 4, 5).toString() + "}");
  }

  @Test
  public void CSVStringTest() {
    assertEquals(move1.toCSVString(), "100,Test," + LocalDate.of(2023, 4, 5));
  }
}
