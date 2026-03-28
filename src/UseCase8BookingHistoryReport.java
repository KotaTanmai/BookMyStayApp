/**
 * UseCase8BookingHistoryReport
 * Version 8.0
 * Demonstrates Booking History and Reporting
 */

import java.util.*;

// Reservation Class
class Reservation {

    String reservationId;
    String guestName;
    String roomType;

    Reservation(
        String reservationId,
        String guestName,
        String roomType
    ) {

        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void displayReservation() {

        System.out.println(
            "Reservation ID : " + reservationId
        );

        System.out.println(
            "Guest Name     : " + guestName
        );

        System.out.println(
            "Room Type      : " + roomType
        );

        System.out.println("-----------------------------");
    }
}

// Booking History Class
class BookingHistory {

    private List<Reservation> history;

    BookingHistory() {

        history = new ArrayList<>();
    }

    // Store confirmed booking
    public void addReservation(
        Reservation reservation
    ) {

        history.add(reservation);

        System.out.println(
            "Reservation Stored : "
            + reservation.reservationId
        );
    }

    // Retrieve all bookings
    public List<Reservation> getHistory() {

        return history;
    }
}

// Report Service
class BookingReportService {

    public void generateReport(
        BookingHistory history
    ) {

        System.out.println(
            "\n--- Booking History Report ---"
        );

        List<Reservation> bookings =
            history.getHistory();

        if (bookings.isEmpty()) {

            System.out.println(
                "No bookings available."
            );

        } else {

            for (Reservation r : bookings) {

                r.displayReservation();
            }

            System.out.println(
                "Total Bookings : "
                + bookings.size()
            );
        }
    }
}

// Main Class
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println(" Hotel Booking System ");
        System.out.println(" Version : v8.0 ");
        System.out.println("================================");

        BookingHistory history =
            new BookingHistory();

        // Add confirmed reservations
        history.addReservation(
            new Reservation(
                "SingleRoom101",
                "Tanmai",
                "Single Room"
            )
        );

        history.addReservation(
            new Reservation(
                "DoubleRoom102",
                "Rahul",
                "Double Room"
            )
        );

        history.addReservation(
            new Reservation(
                "SuiteRoom103",
                "Priya",
                "Suite Room"
            )
        );

        // Generate Report
        BookingReportService reportService =
            new BookingReportService();

        reportService.generateReport(
            history
        );

        System.out.println(
            "\nBooking History Reporting Completed!"
        );
    }
}