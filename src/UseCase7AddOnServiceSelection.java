/**
 * UseCase7AddOnServiceSelection
 * Version 7.0
 * Demonstrates Add-On Service Selection
 */

import java.util.*;

// Service Class
class AddOnService {

    String serviceName;
    double cost;

    AddOnService(String serviceName, double cost) {

        this.serviceName = serviceName;
        this.cost = cost;
    }

    void displayService() {

        System.out.println(
            serviceName + " - ₹" + cost
        );
    }
}

// Manager Class
class AddOnServiceManager {

    // reservationId → List of services
    private HashMap<String, List<AddOnService>> serviceMap;

    AddOnServiceManager() {

        serviceMap = new HashMap<>();
    }

    // Add service to reservation
    public void addService(
        String reservationId,
        AddOnService service
    ) {

        serviceMap.putIfAbsent(
            reservationId,
            new ArrayList<>()
        );

        serviceMap
            .get(reservationId)
            .add(service);

        System.out.println(
            "Service Added: "
            + service.serviceName
            + " for Reservation "
            + reservationId
        );
    }

    // Display services
    public void displayServices(
        String reservationId
    ) {

        System.out.println(
            "\nServices for Reservation "
            + reservationId
        );

        List<AddOnService> services =
            serviceMap.get(reservationId);

        double totalCost = 0;

        if (services != null) {

            for (AddOnService s : services) {

                s.displayService();
                totalCost += s.cost;
            }

            System.out.println(
                "Total Add-On Cost : ₹"
                + totalCost
            );

        } else {

            System.out.println(
                "No services selected."
            );
        }
    }
}

// Main Class
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println(" Hotel Booking System ");
        System.out.println(" Version : v7.0 ");
        System.out.println("================================");

        AddOnServiceManager manager =
            new AddOnServiceManager();

        String reservationId = "SingleRoom101";

        // Add services
        manager.addService(
            reservationId,
            new AddOnService(
                "Breakfast",
                500
            )
        );

        manager.addService(
            reservationId,
            new AddOnService(
                "Airport Pickup",
                800
            )
        );

        manager.addService(
            reservationId,
            new AddOnService(
                "Extra Bed",
                600
            )
        );

        // Display services
        manager.displayServices(
            reservationId
        );

        System.out.println(
            "\nAdd-On Services Process Completed!"
        );
    }
}