/**
 * UseCase6RoomAllocationService
 * Version 6.0
 * Demonstrates Reservation Confirmation and Room Allocation
 */

import java.util.*;

// Reservation Class
class Reservation {

    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {

        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Inventory Service
class RoomInventory {

    private HashMap<String, Integer> inventory;

    RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {

        return inventory.get(roomType);
    }

    public void decrementRoom(String roomType) {

        inventory.put(
            roomType,
            inventory.get(roomType) - 1
        );
    }
}

// Booking Queue
class BookingRequestQueue {

    private Queue<Reservation> queue;

    BookingRequestQueue() {

        queue = new LinkedList<>();
    }

    public void addRequest(Reservation r) {

        queue.add(r);
    }

    public Reservation getNextRequest() {

        return queue.poll(); // FIFO
    }

    public boolean isEmpty() {

        return queue.isEmpty();
    }
}

// Allocation Service
class RoomAllocationService {

    private RoomInventory inventory;

    // Prevent duplicate IDs
    private Set<String> allocatedRoomIds;

    // Track room type → IDs
    private HashMap<String, Set<String>> allocationMap;

    private int roomCounter = 101;

    RoomAllocationService(RoomInventory inventory) {

        this.inventory = inventory;

        allocatedRoomIds = new HashSet<>();

        allocationMap = new HashMap<>();
    }

    public void processBooking(
        BookingRequestQueue queue
    ) {

        System.out.println(
            "\n--- Processing Booking Requests ---"
        );

        while (!queue.isEmpty()) {

            Reservation r =
                queue.getNextRequest();

            int available =
                inventory.getAvailability(
                    r.roomType
                );

            if (available > 0) {

                // Generate Unique Room ID
                String roomId =
                    r.roomType.replace(" ", "")
                    + roomCounter++;

                // Prevent duplicate
                if (!allocatedRoomIds.contains(roomId)) {

                    allocatedRoomIds.add(roomId);

                    allocationMap
                        .putIfAbsent(
                            r.roomType,
                            new HashSet<>()
                        );

                    allocationMap
                        .get(r.roomType)
                        .add(roomId);

                    inventory.decrementRoom(
                        r.roomType
                    );

                    System.out.println(
                        "Booking Confirmed for "
                        + r.guestName
                        + " | Room ID : "
                        + roomId
                    );

                }

            } else {

                System.out.println(
                    "Booking Failed for "
                    + r.guestName
                    + " (No rooms available)"
                );
            }
        }
    }
}

// Main Class
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println(" Hotel Booking System ");
        System.out.println(" Version : v6.0 ");
        System.out.println("================================");

        RoomInventory inventory =
            new RoomInventory();

        BookingRequestQueue queue =
            new BookingRequestQueue();

        // Add requests
        queue.addRequest(
            new Reservation("Tanmai", "Single Room")
        );

        queue.addRequest(
            new Reservation("Rahul", "Double Room")
        );

        queue.addRequest(
            new Reservation("Priya", "Suite Room")
        );

        queue.addRequest(
            new Reservation("Kiran", "Single Room")
        );

        RoomAllocationService allocator =
            new RoomAllocationService(
                inventory
            );

        // Process bookings
        allocator.processBooking(queue);

        System.out.println(
            "\nAll bookings processed!"
        );
    }
}