package edu.wpi.teamname.DAOs;

import edu.wpi.teamname.DAOs.orms.Location;
import edu.wpi.teamname.DAOs.orms.Move;
import edu.wpi.teamname.DAOs.orms.NodeType;
import edu.wpi.teamname.ServiceRequests.ConferenceRoom.ConfRoomDAO;
import edu.wpi.teamname.ServiceRequests.ConferenceRoom.ConfRoomRequest;
import edu.wpi.teamname.ServiceRequests.ConferenceRoom.RoomRequestDAO;
import edu.wpi.teamname.ServiceRequests.flowers.*;
import edu.wpi.teamname.pathfinding.AStar;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;

public class DataBaseRepository {

  private static DataBaseRepository single_instance = null;
  private dbConnection connection;
  AStar pathFinder;
  @Getter NodeDAOImpl nodeDAO;
  @Getter MoveDAOImpl moveDAO;
  @Getter LocationDAOImpl locationDAO;
  @Getter EdgeDAOImpl edgeDAO;
  @Getter RoomRequestDAO roomRequestDAO;
  @Getter ConfRoomDAO confRoomDAO;

  @Getter IDAO<Flower, Integer> flowerDAO;
  @Getter IDAO<FlowerDelivery, Integer> flowerDeliveryDAO;

  private DataBaseRepository() {
    nodeDAO = new NodeDAOImpl();
    moveDAO = new MoveDAOImpl();
    locationDAO = new LocationDAOImpl();
    edgeDAO = new EdgeDAOImpl();
    roomRequestDAO = new RoomRequestDAO();
    confRoomDAO = new ConfRoomDAO();

    flowerDAO = new FlowerDAOImpl();
    flowerDeliveryDAO = new FlowerDeliveryDAOImpl();
  }

  public static synchronized DataBaseRepository getInstance() {
    if (single_instance == null) single_instance = new DataBaseRepository();
    return single_instance;
  }

  public void load() {
    connection = dbConnection.getInstance();
    pathFinder = new AStar();
    nodeDAO.initTable(connection.getNodeTable());
    edgeDAO.initTable(connection.getEdgesTable());
    locationDAO.initTable(connection.getLocationTable());
    moveDAO.initTable(connection.getMoveTable());
    roomRequestDAO.initTable(connection.getRoomReservationsTable());
    confRoomDAO.initTable(connection.getConferenceRoomTables());

    nodeDAO.loadRemote("src/main/java/edu/wpi/teamname/defaultCSV/Node.csv");
    edgeDAO.loadRemote("src/main/java/edu/wpi/teamname/defaultCSV/Edge.csv");
    locationDAO.loadRemote("src/main/java/edu/wpi/teamname/defaultCSV/LocationName.csv");
    moveDAO.loadRemote("src/main/java/edu/wpi/teamname/defaultCSV/Move.csv");

    flowerDAO.initTable(connection.getFlowerTable());
    flowerDAO.loadRemote("src/main/java/edu/wpi/teamname/defaultCSV/Flower.csv");
    flowerDeliveryDAO.initTable(connection.getFlowerDeliveryTable());
    flowerDeliveryDAO.loadRemote("flowersssssss!?");
  }

  public boolean login(String text, String text1) {
    return true;
  }

  public String processMoveRequest(int newLocNodeID, String location, LocalDate date)
      throws Exception {
    if (checkCanMove(location, date)) {
      throw new Exception("Moved that day already");
    } else {
      String moveResult;
      if (date.isAfter(LocalDate.now())) {
        moveResult = "Going to move " + location + " on " + date;
      } else {
        moveResult = "Moved " + location + " to its new location";
      }
      Move thisMove = new Move(newLocNodeID, location, date);
      moveDAO.add(thisMove);
      return moveResult;
    }
  }

  private boolean checkCanMove(String location, LocalDate date) {
    return moveDAO.getMoveHistory().get(location).contains(date);
  }

  public void importCSV(String inputPath) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(inputPath));
    String check = reader.readLine();

    if (check.equals(nodeDAO.getCSVheader())) nodeDAO.importCSV(inputPath);
    else if (check.equals(edgeDAO.getCSVheader())) edgeDAO.importCSV(inputPath);
    else if (check.equals(moveDAO.getCSVheader())) {
      moveDAO.importCSV(inputPath);
    } else if (check.equals(locationDAO.getCSVheader())) {
      locationDAO.importCSV(inputPath);
    } else throw new RuntimeException();
  }

  public void exportCSV(String outputPath) throws IOException {
    nodeDAO.exportCSV(outputPath);
    edgeDAO.exportCSV(outputPath);
    moveDAO.exportCSV(outputPath);
    locationDAO.exportCSV(outputPath);
  }

  public void addRoomRequest(ConfRoomRequest confRoomRequest) throws Exception {

    if (!roomRequestDAO.hasConflicts(
        confRoomRequest.getRoom(),
        confRoomRequest.getEventDate(),
        confRoomRequest.getStartTime(),
        confRoomRequest.getEndTime())) {
      roomRequestDAO.add(confRoomRequest);

    } else {
      throw new Exception();
    }
  }

  public void flowerAdd(Flower flower) {
    flowerDAO.add(flower);
  }

  public List<Flower> getListOfSize(String size) {
    List<Flower> flowers = flowerDAO.getAll();
    List<Flower> sizedFlowers = new ArrayList<>();

    for (Flower flower : flowers) {
      if (flower.getSize().toString().equalsIgnoreCase(size)) {
        sizedFlowers.add(flower);
      }
    }

    return sizedFlowers;
  }

  /*public void addFlowers() {
    Flower flowerf1 =
        new Flower(
            flowerGetNewID(),
            "flower1",
            Size.SMALL,
            50,
            1,
            "message",
            false,
            "description1",
            "image1");
    flowerDAO.add(flowerf1);
    Flower flowerf2 =
        new Flower(
            flowerGetNewID(),
            "flower2",
            Size.MEDIUM,
            100,
            1,
            "message",
            false,
            "description2",
            "image2");
    flowerDAO.add(flowerf2);
    Flower flowerf3 =
        new Flower(
            flowerGetNewID(),
            "flower3",
            Size.SMALL,
            50,
            1,
            "message",
            false,
            "description3",
            "image3");
    flowerDAO.add(flowerf3);
    Flower flowerf4 =
        new Flower(
            flowerGetNewID(),
            "flower4",
            Size.LARGE,
            200,
            1,
            "message",
            false,
            "description4",
            "image4");
    flowerDAO.add(flowerf4);
    Flower flowerf5 =
        new Flower(
            flowerGetNewID(),
            "flower5",
            Size.SMALL,
            50,
            1,
            "message",
            false,
            "description5",
            "image5");
    flowerDAO.add(flowerf5);
  }*/

  public Flower flowerRetrieve(int target) {
    return flowerDAO.getRow(target);
  }

  public void flowerDeliveryAdd(FlowerDelivery fd) {
    flowerDeliveryDAO.add(fd);
  }

  public List<FlowerDelivery> flowerDeliveryGetAll() {
    return flowerDeliveryDAO.getAll();
  }

  public List<String> getListOfEligibleRooms() {
    DataBaseRepository repo = DataBaseRepository.getInstance();

    List<String> listOfEligibleRooms = new ArrayList<>();
    List<Location> locationList = repo.getLocationDAO().getAll();

    NodeType[] nodeTypes = new NodeType[6];
    nodeTypes[0] = NodeType.ELEV;
    nodeTypes[1] = NodeType.EXIT;
    nodeTypes[2] = NodeType.HALL;
    nodeTypes[3] = NodeType.REST;
    nodeTypes[4] = NodeType.STAI;
    nodeTypes[5] = NodeType.BATH;

    boolean isFound;
    for (Location loc : locationList) { // hashmap
      isFound = false;
      for (NodeType nt : nodeTypes) {
        if (loc.getNodeType() == nt) {
          isFound = true;
          break;
        }
      }
      if (!isFound) listOfEligibleRooms.add(loc.getLongName());
    }
    Collections.sort(listOfEligibleRooms);

    return listOfEligibleRooms;
  }

  public int flowerGetNewID() {
    return flowerDAO.getAll().size();
  }

  public int flowerGetNewDeliveryID() {
    return flowerDeliveryDAO.getAll().size();
  }
}
