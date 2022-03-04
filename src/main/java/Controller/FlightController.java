package Controller;

import Service.FlightService;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    public void showAllFlights(){
        flightService.showAllFlights();
    }

    public void showFlightBySerial(String serial){
        flightService.showFlightBySerial(serial);
    }

    public void showFlightsForBooking(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the destination : ");
        String destination = scanner.nextLine();
        System.out.print("Enter number of passengers : ");
        short seats = scanner.nextShort();
        scanner.nextLine();
        System.out.print("Enter the date (Ex: 2022-03-12) : ");
        String stringDate = scanner.nextLine();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
        } catch (ParseException e) {
            System.out.println("Invalid date format");
        }
        flightService.showFlightsForBooking(destination, seats, date);
    }
}
