/**
 * UseCase5BookingRequestQueue
 * Version 5.0
 * Demonstrates Booking Requests using Queue (FIFO)
 */

import java.util.Queue;
import java.util.LinkedList;

// Reservation Class
class Reservation {

    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {

        this.guestName = guestName;
        this.roomType = roomType;
    }

    void displayReservation() {

        System.out.println(
            "Guest : " + guestName +
            " | Room Type : " + roomType
        );
    }
}

// Booking Queue Class
class BookingRequestQueue {

    private Queue<Reservation> queue;

    BookingRequestQueue() {

        queue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(Reservation reservation) {

        queue.add(reservation);

        System.out.println(
            "Request Added for " +
            reservation.guestName
        );
    }

    // Display all requests
    public void displayRequests() {

        System.out.println(
            "\n--- Booking Requests in Queue ---"
        );

        for (Reservation r : queue) {

            r.displayReservation();
        }
    }
}

// Main Class
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println(" Hotel Booking System ");
        System.out.println(" Version : v5.0 ");
        System.out.println("================================");

        BookingRequestQueue bookingQueue =
            new BookingRequestQueue();

        // Add booking requests
        bookingQueue.addRequest(
            new Reservation("Tanmai", "Single Room")
        );

        bookingQueue.addRequest(
            new Reservation("Rahul", "Double Room")
        );

        bookingQueue.addRequest(
            new Reservation("Priya", "Suite Room")
        );

        // Display queue
        bookingQueue.displayRequests();

        System.out.println(
            "\nRequests stored in FIFO order!"
        );
    }
}