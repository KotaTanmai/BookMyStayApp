/**
 * UseCase3InventorySetup
 * Version 3.0
 * Demonstrates Centralized Room Inventory using HashMap
 */

import java.util.HashMap;

// Inventory Class
class RoomInventory {

    // HashMap to store room availability
    private HashMap<String, Integer> inventory;

    // Constructor to initialize inventory
    RoomInventory() {

        inventory = new HashMap<>();

        // Register room types
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Method to get availability
    public int getAvailability(String roomType) {

        return inventory.get(roomType);
    }

    // Method to update availability
    public void updateAvailability(String roomType, int newCount) {

        inventory.put(roomType, newCount);
    }

    // Method to display inventory
    public void displayInventory() {

        System.out.println("\n--- Current Room Inventory ---");

        for (String roomType : inventory.keySet()) {

            System.out.println(
                roomType + " : " + inventory.get(roomType)
            );
        }
    }
}

// Main Class
public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println(" Hotel Booking System ");
        System.out.println(" Version : v3.0 ");
        System.out.println("================================");

        // Initialize Inventory
        RoomInventory inventory = new RoomInventory();

        // Display inventory
        inventory.displayInventory();

        // Example update
        inventory.updateAvailability("Single Room", 4);

        System.out.println("\nAfter Updating Single Room:");

        inventory.displayInventory();

        System.out.println("\nApplication Terminated Successfully!");
    }
}