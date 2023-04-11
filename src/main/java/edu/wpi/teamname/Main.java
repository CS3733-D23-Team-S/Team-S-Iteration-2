package edu.wpi.teamname;

import edu.wpi.teamname.ServiceRequests.ConferenceRoom.ConfRoomDAO;
import edu.wpi.teamname.ServiceRequests.ConferenceRoom.ConfRoomLocation;
import edu.wpi.teamname.ServiceRequests.ConferenceRoom.ConfRoomRequest;
import edu.wpi.teamname.ServiceRequests.ConferenceRoom.RoomRequestDAO;
import edu.wpi.teamname.databaseredo.DataBaseRepository;
import edu.wpi.teamname.databaseredo.dbConnection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws Exception {
    DataBaseRepository database = DataBaseRepository.getInstance();
    database.load();
    App.launch(App.class, args);

//    LocalDate startDate = LocalDate.of(2022, 5, 13);
//    LocalTime startTime = LocalTime.of(14, 50);
//    LocalTime endTime = LocalTime.of(15, 35);
//
//    ConfRoomRequest confRoomRequest =
//        new ConfRoomRequest(
//            LocalDate.of(2022, 5, 13),
//            LocalTime.of(14, 45),
//            LocalTime.of(15, 10),
//            "BTM Conference Center",
//            "staff",
//            "nothing",
//            "Yes",
//            "staff");
//    database.getRoomRequestDAO().add(confRoomRequest);
//    RoomRequestDAO roomRequestDAO = database.getRoomRequestDAO();
//    ConfRoomDAO confRoomDAO = database.getConfRoomDAO();
//    ArrayList<ConfRoomLocation> roomLocations = new ArrayList<>();
//    System.out.println(roomRequestDAO.getAll());
//    App.launch(App.class, args);
//
//    System.out.println(roomLocations);

    // Debugging stuff in order to check everything looks about right
    System.out.println("Loaded everything");
    dbConnection.getInstance().getConnection().close();
    //    for (int key : NodeDaoImpl.getInstance().getNodes().keySet())
    //      System.out.println(NodeDaoImpl.getInstance().getNodes().get(key).toString());
    //    for (String key : MoveDaoImpl.getInstance().getMoves().keySet())
    //      System.out.println(MoveDaoImpl.getInstance().getMoves().get(key).toString());
    //    for (int key : EdgeDaoImpl.getInstance().getNeighbors().keySet()) {
    //      System.out.print(key);
    //      System.out.print("\t Neighbors:\t");
    //      System.out.println(EdgeDaoImpl.getInstance().getNeighbors().get(key).toString());
    //    }

  }
}
