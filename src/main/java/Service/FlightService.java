package Service;

import Dao.BookingDao;
import Dao.FlightDao;
import Dao.PassengerDao;
import Model.Flight;
import Model.Passenger;
import org.postgresql.jdbc2.ArrayAssistant;
import util.DataParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FlightService implements FlightServiceImpl {

    private final FlightDao flightDao;
    private final PassengerDao passengerDao = new PassengerDao();
    private final PassengerService passengerService = new PassengerService(passengerDao);
    private final BookingDao bookingDao = new BookingDao();
    private final BookingService bookingService = new BookingService(bookingDao);
    final Scanner scanner = new Scanner(System.in);


    public FlightService(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    @Override
    public void showAllFlights() {
        ResultSet resultSet = flightDao.showAllFlights();
        ArrayList<Flight> flights = DataParser.parseFlightResultSet(resultSet);

        System.out.println("Serial           From              Destination");
        System.out.println("-------        ---------          -------------");
        for (int i = 0; i < flights.size(); i++) {
            System.out.println(flights.get(i).getSerial_number() + "          " +
                    flights.get(i).getFrom() + "                 " + flights.get(i).getDestination());
        }
        System.out.println("----------------------------------------------------");
    }

    @Override
    public void showFlightBySerial(String serial) {
        ResultSet resultSet = flightDao.showAllFlights();
        ArrayList<Flight> flights = DataParser.parseFlightResultSet(resultSet);
        System.out.println("Serial           From              Destination          Seats           Date");
        System.out.println("-------        ---------          -------------        -------         -------");
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getSerial_number().equals(serial)) {
                System.out.println(flights.get(i).getSerial_number() + "          " +
                        flights.get(i).getFrom() + "                 " + flights.get(i).getDestination() +
                        "             " + flights.get(i).getSeats() + "            " + flights.get(i).getDate());
            }

        }
        System.out.println("-----------------------------------------------------------------------");
    }

    @Override
    public void showFlightsForBooking(String destination, short seats, Date date) {
        ResultSet resultSet = flightDao.showAllFlights();
        ArrayList<Flight> flights = DataParser.parseFlightResultSet(resultSet);
        ArrayList<Flight> selectedFlights = new ArrayList<>();
        System.out.println("Serial           From              Destination          Seats           Date");
        System.out.println("-------        ---------          -------------        -------         -------");

        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getDestination().equals(destination) && flights.get(i).getSeats() >= seats &&
                    flights.get(i).getDate().equals(date)) {
                selectedFlights.add(flights.get(i));
                System.out.println(flights.get(i).getSerial_number() + "          " +
                        flights.get(i).getFrom() + "                 " + flights.get(i).getDestination() +
                        "             " + flights.get(i).getSeats() + "            " + flights.get(i).getDate());
            }
        }
        if (selectedFlights.size() == 0) {
            System.out.println("There is no flight for these conditions");
        } else {
            System.out.print("Enter the serial number for booking or enter 0 to return main menu : ");
            String s = scanner.nextLine();
            if (s.equals("0")) {
                return;
            } else {
                List<Flight> bookingFlight = selectedFlights.stream().filter(flight -> flight.getSerial_number().equals(s)).collect(Collectors.toList());
                if (bookingFlight.size() == 0) {
                    System.out.println("Please enter valid serial number");
                    return;
                } else {
                    ArrayList<Passenger> bookingPassengers = new ArrayList<>();
                    int count = 0;
                    short count2 = 0;
                    for (int i = 0; i < seats; i++) {
                        System.out.print("Enter fin code and  full name of passenger : ");
                        String fullName = scanner.nextLine();
                        String[] nameSurnameFin = fullName.split(" ");
                        ArrayList<Passenger> passengers = passengerService.getAllPassengers();
                        for (int j = 0; j < passengers.size(); j++) {
                            if (passengers.get(j).getFinCode().equals(nameSurnameFin[0])) {
                                bookingPassengers.add(passengers.get(j));
                                count++;
                                break;
                            }
                        }
                        if (count == 0) {
                            passengerService.insertPassenger(nameSurnameFin[1], nameSurnameFin[2], nameSurnameFin[0]);
                            count2++;
                        }

                    }
                    ArrayList<Integer> ids = passengerService.getLastPassengers(count2);
                    for (int a = 0; a < bookingPassengers.size(); a++) {
                        ids.add(bookingPassengers.get(a).getId());
                    }
                    for (int i = 0; i < ids.size(); i++) {
                        if (bookingService.getPassengerId(ids.get(i), s) == 0) {
                            bookingService.createBooking(ids.get(i), s);
                            flightDao.updateSeats(s, (short) (getFlightSeatsCount(s) - 1));
                        }

                    }
                }
            }
        }
        System.out.println("------------------------------------------------------------------------------");
    }

    @Override
    public int getFlightSeatsCount(String serialNumber) {
        short seats = 0;
        ResultSet resultSet = flightDao.getFlightSeatCount(serialNumber);
        try {
            while (resultSet.next()) {
                seats = resultSet.getShort("seats");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return seats;
    }
}
