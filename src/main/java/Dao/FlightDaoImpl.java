package Dao;

import Model.Flight;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface FlightDaoImpl {
    ResultSet showAllFlights();
    ResultSet showFlightBySerial(String serial);
    boolean updateSeats(String serialNumber, short newSeats);
    ResultSet getFlightSeatCount(String serialNumber);
}
