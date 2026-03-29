/**
 * UseCase9ErrorHandlingValidation
 * Version 9.0
 * Demonstrates Error Handling and Validation
 */

import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {

    InvalidBookingException(String message) {

        super(message);
    }
}

// Inventory Class
class RoomInventory {

    private HashMap<String, Integer> inventory;

    RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 0);
    }

    // Validate room type
    public void validateRoomType(
        String roomType
    ) throws InvalidBookingException {

        if (!inventory.containsKey(roomType)) {

            throw new InvalidBookingException(
                "Invalid Room Type : "
                + roomType
            );
        }
    }

    // Check availability
    public void validateAvailability(
        String roomType
    ) throws InvalidBookingException {

        int available =
            inventory.get(roomType);

        if (available <= 0) {

            throw new InvalidBookingException(
                "No rooms available for "
                + roomType
            );
        }
    }

    // Allocate room
    public void allocateRoom(
        String roomType
    ) throws InvalidBookingException {

        validateRoomType(roomType);
        validateAvailability(roomType);

        inventory.put(
            roomType,
            inventory.get(roomType) - 1
        );

        System.out.println(
            "Room allocated successfully for "
            + roomType
        );
    }
}

// Main Class
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println(" Hotel Booking System ");
        System.out.println(" Version : v9.0 ");
        System.out.println("================================");

        RoomInventory inventory =
            new RoomInventory();

        // Test inputs
        String[] testBookings = {
            "Single Room",
            "Suite Room",
            "Invalid Room"
        };

        for (String roomType : testBookings) {

            try {

                System.out.println(
                    "\nProcessing booking for : "
                    + roomType
                );

                inventory.allocateRoom(roomType);

            } catch (InvalidBookingException e) {

                System.out.println(
                    "Booking Failed : "
                    + e.getMessage()
                );
            }
        }

        System.out.println(
            "\nError Handling Completed Safely!"
        );
    }
}