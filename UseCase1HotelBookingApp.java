/**
 * UseCase1HotelBookingApp
 * Version 2.0
 * Room Types & Static Availability
 */

// Abstract Room Class
abstract class Room {

    String roomType;
    int beds;
    double price;

    Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    abstract void displayRoomDetails();
}

// Single Room
class SingleRoom extends Room {

    SingleRoom() {
        super("Single Room", 1, 1500);
    }

    void displayRoomDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Price     : ₹" + price);
    }
}

// Double Room
class DoubleRoom extends Room {

    DoubleRoom() {
        super("Double Room", 2, 2500);
    }

    void displayRoomDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Price     : ₹" + price);
    }
}

// Suite Room
class SuiteRoom extends Room {

    SuiteRoom() {
        super("Suite Room", 3, 5000);
    }

    void displayRoomDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Price     : ₹" + price);
    }
}

// Main Class
public class UseCase1HotelBookingApp {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println(" Hotel Booking System ");
        System.out.println(" Version : v2.0 ");
        System.out.println("================================");

        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        System.out.println("\n--- Room Details ---");

        single.displayRoomDetails();
        System.out.println("Available : " + singleAvailability);

        System.out.println();

        doubleRoom.displayRoomDetails();
        System.out.println("Available : " + doubleAvailability);

        System.out.println();

        suite.displayRoomDetails();
        System.out.println("Available : " + suiteAvailability);

        System.out.println("\nApplication Terminated Successfully!");
    }
}