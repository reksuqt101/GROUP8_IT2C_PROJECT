import java.util.Scanner;
public class Main {
    static Scanner input = new Scanner(System.in);
    // Driver information
    static String[] driverNames = new String[20];
    static String[] driverStatus = new String[20];
    static int driverCount = 0;
    // Vehicle information
    static String[] vehiclePlate = new String[20];
    static String[] vehicleStatus = new String[20];
    static int vehicleCount = 0;
    // Route information
    static String[] routeNames = new String[20];
    static String[] routeTimes = new String[20];
    static int routeCount = 0;
    // Dispatch information
    static String[] dispatchDriver = new String[20];
    static String[] dispatchVehicle = new String[20];
    static String[] dispatchRoute = new String[20];
    static String[] dispatchTime = new String[20];
    static String[] dispatchStatus = new String[20];
    static int dispatchCount = 0;
    // =========================
    // REGISTER DRIVER
    // =========================
    static void registerDriver() {
        if (driverCount >= 20) {
            System.out.println("Driver storage is full.");
            return;
        }
        System.out.println("\n===== REGISTER DRIVER =====");
        System.out.print("Enter driver name: ");
        driverNames[driverCount] = input.nextLine();
        if (driverNames[driverCount].isEmpty()) {
            System.out.println("Driver name cannot be empty.");
            return;
        }
        driverStatus[driverCount] = "Available";
        driverCount++;
        System.out.println("Driver registered successfully.");
    }
    // =========================
    // REGISTER VEHICLE
    // =========================
    static void registerVehicle() {
        if (vehicleCount >= 20) {
            System.out.println("Vehicle storage is full.");
            return;
        }
        System.out.println("\n===== REGISTER VEHICLE =====");
        System.out.print("Enter vehicle plate number: ");
        vehiclePlate[vehicleCount] = input.nextLine();
        if (vehiclePlate[vehicleCount].isEmpty()) {
            System.out.println("Plate number cannot be empty.");
            return;
        }
        vehicleStatus[vehicleCount] = "Available";
        vehicleCount++;
        System.out.println("Vehicle registered successfully.");
    }
    // =========================
    // ADD ROUTE
    // =========================
    static void addRoute() {
        if (routeCount >= 20) {
            System.out.println("Route storage is full.");
            return;
        }
        System.out.println("\n===== ADD ROUTE SCHEDULE =====");
        System.out.print("Enter route: ");
        routeNames[routeCount] = input.nextLine();
        System.out.print("Enter departure time: ");
        routeTimes[routeCount] = input.nextLine();
        if (routeNames[routeCount].isEmpty()
                || routeTimes[routeCount].isEmpty()) {
            System.out.println("Route and time cannot be empty.");
            return;
        }
        routeCount++;
        System.out.println("Route scheduled successfully.");
    }
    // =========================
    // VIEW DRIVERS
    // =========================
    static void viewDrivers() {
        System.out.println("\n===== DRIVER LIST =====");
        if (driverCount == 0) {
            System.out.println("No drivers registered.");
            return;
        }
        for (int i = 0; i < driverCount; i++) {
            System.out.println(
                    (i + 1) + ". " +
                            driverNames[i] + " - " +
                            driverStatus[i]
            );
        }
    }
    // =========================
    // VIEW VEHICLES
    // =========================
    static void viewVehicles() {
        System.out.println("\n===== VEHICLE LIST =====");
        if (vehicleCount == 0) {
            System.out.println("No vehicles registered.");
            return;
        }
        for (int i = 0; i < vehicleCount; i++) {
            System.out.println(
                    (i + 1) + ". " +
                            vehiclePlate[i] + " - " +
                            vehicleStatus[i]
            );
        }
    }
    // =========================
    // VIEW ROUTES
    // =========================
    static void viewRoutes() {
        System.out.println("\n===== ROUTE SCHEDULE =====");
        if (routeCount == 0) {
            System.out.println("No routes scheduled.");
            return;
        }
        for (int i = 0; i < routeCount; i++) {
            System.out.println(
                    (i + 1) + ". " +
                            routeNames[i] + " - " +
                            routeTimes[i]
            );
        }
    }
    // =========================
    // DISPATCH VEHICLE
    // =========================
    static void dispatchVehicle() {
        System.out.println("\n===== DISPATCH VEHICLE =====");
        if (driverCount == 0 || vehicleCount == 0 || routeCount == 0) {
            System.out.println(
                    "You need at least one driver, vehicle, and route."
            );
            return;
        }
        // Find an available driver
        int driverIndex = -1;
        for (int i = 0; i < driverCount; i++) {
            if (driverStatus[i].equals("Available")) {
                driverIndex = i;
                break;
            }
        }
        if (driverIndex == -1) {
            System.out.println("No available drivers.");
            return;
        }
        // Find an available vehicle
        int vehicleIndex = -1;
        for (int i = 0; i < vehicleCount; i++) {
            if (vehicleStatus[i].equals("Available")) {
                vehicleIndex = i;
                break;
            }
        }
        if (vehicleIndex == -1) {
            System.out.println("No available vehicles.");
            return;
        }
        viewRoutes();
        System.out.print("Select route number: ");
        int routeChoice = input.nextInt();
        input.nextLine();
        if (routeChoice < 1 || routeChoice > routeCount) {
            System.out.println("Invalid route.");
            return;
        }
        int routeIndex = routeChoice - 1;
        // Save dispatch information
        dispatchDriver[dispatchCount] = driverNames[driverIndex];
        dispatchVehicle[dispatchCount] = vehiclePlate[vehicleIndex];
        dispatchRoute[dispatchCount] = routeNames[routeIndex];
        dispatchTime[dispatchCount] = routeTimes[routeIndex];
        dispatchStatus[dispatchCount] = "Dispatched";
        dispatchCount++;
        // Change status
        driverStatus[driverIndex] = "On Trip";
        vehicleStatus[vehicleIndex] = "On Trip";
        System.out.println("\n===== DISPATCH SUCCESSFUL =====");
        System.out.println("Driver : " + driverNames[driverIndex]);
        System.out.println("Vehicle: " + vehiclePlate[vehicleIndex]);
        System.out.println("Route : " + routeNames[routeIndex]);
        System.out.println("Time : " + routeTimes[routeIndex]);
    }
    // =========================
    // COMPLETE TRIP
    // =========================
    static void completeTrip() {
        System.out.println("\n===== COMPLETE TRIP =====");
        if (dispatchCount == 0) {
            System.out.println("No dispatch records.");
            return;
        }
        int activeTrip = -1;
        for (int i = 0; i < dispatchCount; i++) {
            if (dispatchStatus[i].equals("Dispatched")) {
                activeTrip = i;
                break;
            }
        }
        if (activeTrip == -1) {
            System.out.println("There are no active trips.");
            return;
        }
        System.out.println(
                "Driver : " + dispatchDriver[activeTrip]
        );
        System.out.println(
                "Vehicle: " + dispatchVehicle[activeTrip]
        );
        System.out.println(
                "Route : " + dispatchRoute[activeTrip]
        );
        dispatchStatus[activeTrip] = "Completed";
        // Return driver to Available
        for (int i = 0; i < driverCount; i++) {
            if (driverNames[i].equals(dispatchDriver[activeTrip])) {
                driverStatus[i] = "Available";
            }
        }
        // Return vehicle to Available
        for (int i = 0; i < vehicleCount; i++) {
            if (vehiclePlate[i].equals(dispatchVehicle[activeTrip])) {
                vehicleStatus[i] = "Available";
            }
        }
        System.out.println("Trip completed successfully.");
    }
    // =========================
    // VIEW DISPATCH RECORDS
    // =========================
    static void viewDispatchRecords() {
        System.out.println("\n===== DISPATCH RECORDS =====");
        if (dispatchCount == 0) {
            System.out.println("No dispatch records.");
            return;
        }
        for (int i = 0; i < dispatchCount; i++) {
            System.out.println("\nTrip " + (i + 1));
            System.out.println("Driver : " + dispatchDriver[i]);
            System.out.println("Vehicle: " + dispatchVehicle[i]);
            System.out.println("Route : " + dispatchRoute[i]);
            System.out.println("Time : " + dispatchTime[i]);
            System.out.println("Status : " + dispatchStatus[i]);
        }
    }
    // =========================
    // MAIN PROGRAM
    // =========================
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n======================================");
            System.out.println(" BUS-T IN TIME");
            System.out.println(" Fleet Operations, Dispatch & Transit");
            System.out.println("======================================");
            System.out.println("1. Register Driver");
            System.out.println("2. Register Vehicle");
            System.out.println("3. Add Route Schedule");
            System.out.println("4. View Drivers");
            System.out.println("5. View Vehicles");
            System.out.println("6. View Routes");
            System.out.println("7. Dispatch Vehicle");
            System.out.println("8. Complete Trip");
            System.out.println("9. View Dispatch Records");
            System.out.println("10. Exit");
            System.out.print("\nEnter your choice: ");
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    registerDriver();
                    break;
                case 2:
                    registerVehicle();
                    break;
                case 3:
                    addRoute();
                    break;
                case 4:
                    viewDrivers();
                    break;
                case 5:
                    viewVehicles();
                    break;
                case 6:
                    viewRoutes();
                    break;
                case 7:
                    dispatchVehicle();
                    break;
                case 8:
                    completeTrip();
                    break;
                case 9:
                    viewDispatchRecords();
                    break;
                case 10:
                    System.out.println(
                            "\nThank you for using Bus-t in Time!"
                    );
                    input.close();
                    return;
                default:
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }
    }
}