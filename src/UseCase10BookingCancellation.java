/**
 * UseCase10BookingCancellation
 * Version 10.0
 * Demonstrates Booking Cancellation and Rollback
 */

import java.util.*;

// Reservation Class
class Reservation {

    String reservationId;
    String roomType;

    Reservation(
        String reservationId,
        String roomType
    ) {

        this.reservationId = reservationId;
        this.roomType = roomType;
    }
}

// Inventory Class
class RoomInventory {

    private HashMap<String, Integer> inventory;

    RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public void allocateRoom(
        String roomType
    ) {

        int available =
            inventory.get(roomType);

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

    // Rollback inventory
    public void releaseRoom(
        String roomType
    ) {

        inventory.put(
            roomType,
            inventory.get(roomType) + 1
        );

        System.out.println(
            "Inventory restored for "
            + roomType
        );
    }

    public void displayInventory() {

        System.out.println(
            "\nCurrent Inventory:"
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

// Cancellation Service
class CancellationService {

    private Stack<String> rollbackStack;
    private HashMap<String, Reservation> reservations;
    private RoomInventory inventory;

    CancellationService(
        RoomInventory inventory
    ) {

        this.inventory = inventory;

        rollbackStack = new Stack<>();
        reservations = new HashMap<>();
    }

    // Confirm reservation
    public void confirmReservation(
        String reservationId,
        String roomType
    ) {

        inventory.allocateRoom(roomType);

        Reservation reservation =
            new Reservation(
                reservationId,
                roomType
            );

        reservations.put(
            reservationId,
            reservation
        );

        rollbackStack.push(
            reservationId
        );

        System.out.println(
            "Reservation confirmed : "
            + reservationId
        );
    }

    // Cancel reservation
    public void cancelReservation(
        String reservationId
    ) {

        if (!reservations.containsKey(reservationId)) {

            System.out.println(
                "Cancellation Failed : "
                + "Reservation not found -> "
                + reservationId
            );

            return;
        }

        Reservation reservation =
            reservations.get(reservationId);

        inventory.releaseRoom(
            reservation.roomType
        );

        reservations.remove(reservationId);

        rollbackStack.pop();

        System.out.println(
            "Reservation cancelled : "
            + reservationId
        );
    }
}

// Main Class
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println(" Hotel Booking System ");
        System.out.println(" Version : v10.0 ");
        System.out.println("================================");

        RoomInventory inventory =
            new RoomInventory();

        CancellationService service =
            new CancellationService(
                inventory
            );

        // Confirm Bookings
        service.confirmReservation(
            "R101",
            "Single Room"
        );

        service.confirmReservation(
            "R102",
            "Double Room"
        );

        inventory.displayInventory();

        // Cancel Booking
        service.cancelReservation("R101");

        // Try invalid cancel
        service.cancelReservation("R999");

        inventory.displayInventory();

        System.out.println(
            "\nCancellation & Rollback Completed!"
        );
    }
}