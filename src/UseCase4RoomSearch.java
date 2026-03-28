/**
 * UseCase4RoomSearch
 * Version 4.0
 * Demonstrates Room Search with Read-Only Access
 */

import java.util.HashMap;

// Room Class (Domain Model)
class Room {

    String roomType;
    int beds;
    double price;

    Room(String roomType, int beds, double price) {

        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    void displayRoomDetails() {

        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Price     : ₹" + price);
    }
}

// Inventory Class (Read-only usage)
class RoomInventory {

    private HashMap<String, Integer> inventory;

    RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 0); // example unavailable
    }

    // Get availability only
    public int getAvailability(String roomType) {

        return inventory.get(roomType);
    }

    // Get all room types
    public HashMap<String, Integer> getInventory() {

        return inventory;
    }
}

// Search Service
class RoomSearchService {

    private RoomInventory inventory;

    RoomSearchService(RoomInventory inventory) {

        this.inventory = inventory;
    }

    public void searchAvailableRooms() {

        System.out.println("\n--- Available Rooms ---");

        // Create room objects
        Room single = new Room("Single Room", 1, 1500);
        Room doubleRoom = new Room("Double Room", 2, 2500);
        Room suite = new Room("Suite Room", 3, 5000);

        Room[] rooms = {single, doubleRoom, suite};

        for (Room room : rooms) {

            int available =
                inventory.getAvailability(room.roomType);

            if (available > 0) {

                room.displayRoomDetails();
                System.out.println(
                    "Available : " + available
                );
                System.out.println();
            }
        }
    }
}

// Main Class
public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println(" Hotel Booking System ");
        System.out.println(" Version : v4.0 ");
        System.out.println("================================");

        RoomInventory inventory =
            new RoomInventory();

        RoomSearchService searchService =
            new RoomSearchService(inventory);

        // Perform search
        searchService.searchAvailableRooms();

        System.out.println(
            "Search Completed Successfully!"
        );
    }
}