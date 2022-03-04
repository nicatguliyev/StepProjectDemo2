package Service;

import Model.Passenger;

import java.util.ArrayList;

public interface PassengerServiceImpl {
    void insertPassenger(String firstname, String lastname, String finCode);
    ArrayList<Integer> getLastPassengers(short seats);
    ArrayList<Passenger> getAllPassengers();
    void showAllBookings(String finCode);
}
