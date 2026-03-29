/**
 * UseCase12DataPersistenceRecovery
 * Version 12.0
 * Demonstrates Data Persistence and Recovery
 */

import java.io.*;
import java.util.*;

// Serializable Inventory Class
class RoomInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    private HashMap<String, Integer> inventory;

    RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public void allocateRoom(String roomType) {

        int available =
            inventory.getOrDefault(
                roomType,
                0
            );

        if (available > 0) {

            inventory.put(
                roomType,
                available - 1
            );

            System.out.println(
                "Room allocated for "
                + roomType
            );

        } else {

            System.out.println(
                "No rooms available for "
                + roomType
            );
        }
    }

    public void displayInventory() {

        System.out.println(
            "\nCurrent Inventory State:"
        );

        for (
            Map.Entry<String, Integer> entry
            : inventory.entrySet()
        ) {

            System.out.println(
                entry.getKey()
                + " : "
                + entry.getValue()
            );
        }
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME =
        "inventory_backup.ser";

    // Save Data
    public void saveInventory(
        RoomInventory inventory
    ) {

        try (

            ObjectOutputStream oos =
                new ObjectOutputStream(
                    new FileOutputStream(FILE_NAME)
                )

        ) {

            oos.writeObject(inventory);

            System.out.println(
                "\nInventory saved successfully."
            );

        } catch (IOException e) {

            System.out.println(
                "Error saving inventory: "
                + e.getMessage()
            );
        }
    }

    // Load Data
    public RoomInventory loadInventory() {

        try (

            ObjectInputStream ois =
                new ObjectInputStream(
                    new FileInputStream(FILE_NAME)
                )

        ) {

            RoomInventory inventory =
                (RoomInventory) ois.readObject();

            System.out.println(
                "\nInventory loaded successfully."
            );

            return inventory;

        } catch (Exception e) {

            System.out.println(
                "\nNo previous data found. "
                + "Starting fresh."
            );

            return new RoomInventory();
        }
    }
}

// Main Class
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println(" Hotel Booking System ");
        System.out.println(" Version : v12.0 ");
        System.out.println("================================");

        PersistenceService persistence =
            new PersistenceService();

        // Load existing data
        RoomInventory inventory =
            persistence.loadInventory();

        inventory.displayInventory();

        // Simulate Booking
        inventory.allocateRoom("Single Room");

        inventory.displayInventory();

        // Save before shutdown
        persistence.saveInventory(inventory);

        System.out.println(
            "\nData Persistence & Recovery Completed!"
        );
    }
}