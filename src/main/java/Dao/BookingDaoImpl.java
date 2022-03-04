package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface BookingDaoImpl {
   boolean createBooking(int passengerId, String serialNumber);
   ResultSet getPassengerId(int passengerId, String serialNumber);
   boolean cancelBooking(int bookingId);
}
