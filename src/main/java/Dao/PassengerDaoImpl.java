package Dao;

import Model.Passenger;

import java.sql.ResultSet;

public interface PassengerDaoImpl {
      boolean insertPassenger(String firstname, String lastname, String finCode);
      ResultSet getLastPassengers(short seats);
      ResultSet getAllPassengers();
      ResultSet getPassengersByFin(String finCode);
      ResultSet showAllBookings(String finCode);
}
