package Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public interface FlightServiceImpl {
    void showAllFlights() throws SQLException;
    void showFlightBySerial(String serial);
    void showFlightsForBooking(String destination, short seats, Date date);
    int  getFlightSeatsCount(String serialNumber);
}
