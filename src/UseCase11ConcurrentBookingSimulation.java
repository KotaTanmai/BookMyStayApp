/**
 * UseCase11ConcurrentBookingSimulation
 * Version 11.0
 * Demonstrates Concurrent Booking Simulation (Thread Safety)
 */

import java.util.*;

// Booking Request
class BookingRequest {

    String guestName;
    String roomType;

    BookingRequest(
        String guestName,
        String roomType
    ) {

        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Thread-Safe Inventory
class RoomInventory {

    private HashMap<String, Integer> inventory;

    RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    // Critical Section (Synchronized)
    public synchronized void allocateRoom(
        BookingRequest request
    ) {

        int available =
            inventory.getOrDefault(
                request.roomType,
                0
            );

        if (available > 0) {

            inventory.put(
                request.roomType,
                available - 1
            );

            System.out.println(
                request.guestName
                + " successfully booked "
                + request.roomType
            );

        } else {

            System.out.println(
                request.guestName
                + " failed to book "
                + request.roomType
                + " (No availability)"
            );
        }
    }

    public void displayInventory() {

        System.out.println(
            "\nFinal Inventory State:"
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

// Booking Processor Thread
class BookingProcessor extends Thread {

    private Queue<BookingRequest> queue;
    private RoomInventory inventory;

    BookingProcessor(
        Queue<BookingRequest> queue,
        RoomInventory inventory
    ) {

        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {

        while (true) {

            BookingRequest request;

            synchronized (queue) {

                if (queue.isEmpty()) {

                    break;
                }

                request = queue.poll();
            }

            inventory.allocateRoom(request);
        }
    }
}

// Main Class
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println(" Hotel Booking System ");
        System.out.println(" Version : v11.0 ");
        System.out.println("================================");

        // Shared Queue
        Queue<BookingRequest> bookingQueue =
            new LinkedList<>();

        // Add Requests
        bookingQueue.add(
            new BookingRequest(
                "Tanmai",
                "Single Room"
            )
        );

        bookingQueue.add(
            new BookingRequest(
                "Rahul",
                "Single Room"
            )
        );

        bookingQueue.add(
            new BookingRequest(
                "Priya",
                "Single Room"
            )
        );

        bookingQueue.add(
            new BookingRequest(
                "Amit",
                "Double Room"
            )
        );

        bookingQueue.add(
            new BookingRequest(
                "Neha",
                "Double Room"
            )
        );

        RoomInventory inventory =
            new RoomInventory();

        // Multiple Threads
        BookingProcessor t1 =
            new BookingProcessor(
                bookingQueue,
                inventory
            );

        BookingProcessor t2 =
            new BookingProcessor(
                bookingQueue,
                inventory
            );

        BookingProcessor t3 =
            new BookingProcessor(
                bookingQueue,
                inventory
            );

        // Start Threads
        t1.start();
        t2.start();
        t3.start();

        // Wait for Completion
        try {

            t1.join();
            t2.join();
            t3.join();

        } catch (InterruptedException e) {

            System.out.println(
                "Thread interrupted."
            );
        }

        inventory.displayInventory();

        System.out.println(
            "\nConcurrent Booking Simulation Completed!"
        );
    }
}