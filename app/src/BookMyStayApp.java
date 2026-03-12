/**
 * BookMyStay Application
 * Demonstrates Room Initialization and Static Availability
 *
 * @author Tanmai
 * @version 2.1
 */

// Abstract Room class
abstract class Room {

    String roomType;
    int beds;
    double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Price     : $" + price);
    }
}


// Single Room
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 100);
    }
}


// Double Room
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 180);
    }
}


// Suite Room
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 300);
    }
}



public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("  BookMyStay Hotel System v2.1  ");
        System.out.println("=================================");


        // Room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();


        // Static availability variables
        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;


        System.out.println("\n--- Room Details ---");

        single.displayRoomDetails();
        System.out.println("Available Rooms : " + singleAvailability);
        System.out.println();

        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms : " + doubleAvailability);
        System.out.println();

        suite.displayRoomDetails();
        System.out.println("Available Rooms : " + suiteAvailability);
        System.out.println();

        System.out.println("Application Finished.");

    }
}