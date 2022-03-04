import Controller.BookingController;
import Controller.FlightController;
import Controller.PassengerController;
import Dao.BookingDao;
import Dao.FlightDao;
import Dao.PassengerDao;
import Service.BookingService;
import Service.FlightService;
import Service.PassengerService;
import util.MainMenu;

import java.awt.*;
import java.util.Scanner;

public class Main {
     static FlightDao flightDao = new FlightDao();
     static FlightService flightService = new FlightService(flightDao);
     static FlightController flightController = new FlightController(flightService);
     static BookingDao bookingDao = new BookingDao();
     static BookingService bookingService = new BookingService(bookingDao);
     static BookingController bookingController = new BookingController(bookingService);
     static PassengerDao passengerDao = new PassengerDao();
     static PassengerService passengerService = new PassengerService(passengerDao);
     static PassengerController passengerController = new PassengerController(passengerService);

    public static void main(String[] args) {
        flightController.showAllFlights();
        MainMenu.showMenu();
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.print("Enter the command : ");
            int number = scanner.nextInt();
            scanner.nextLine();
            switch (number){
                case 1:
                    flightController.showAllFlights();
                    MainMenu.showMenu();
                    break;
                case 2:
                    System.out.print("Enter the serial number of flight : ");
                    flightController.showFlightBySerial(scanner.nextLine());
                    MainMenu.showMenu();
                    break;
                case 3:
                    flightController.showFlightsForBooking();
                    MainMenu.showMenu();
                    break;
                case 4:
                    bookingController.cancelBooking();
                    MainMenu.showMenu();
                    break;
                case 5:
                    passengerController.showAllBookings();
                    MainMenu.showMenu();
                    break;
                case 6:
                    System.out.println("GO HOME BABY");
                    return;
            }
        }
    }
}
