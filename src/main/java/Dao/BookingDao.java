package Dao;

import util.SqlConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookingDao  implements BookingDaoImpl{
    @Override
    public boolean createBooking(int passengerId, String serialNumber) {
        Connection connection = SqlConnection.createConnection();
            Boolean bool = false;
            try {
                Statement statement = connection.createStatement();
                bool = statement.execute(String.format("Insert Into Booking (passenger_id, serial_number) " +
                        "values('%d' , '%s')", passengerId, serialNumber));
                return bool;
            } catch (SQLException e) {
                e.printStackTrace();
                return true;
            }


    }

    @Override
    public ResultSet getPassengerId(int passengerId, String serialNumber) {
        Connection connection = SqlConnection.createConnection();
        Boolean bool = false;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("Select count(passenger_id) from Booking where passenger_id  = %s" +
                            "and  serial_number = '%s'",
                   passengerId, serialNumber));
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean cancelBooking(int bookingId) {
        Connection connection = SqlConnection.createConnection();
        try
        {
            Statement statement = connection.createStatement();
            statement.execute(String.format("Delete from Booking where id = '%d'", bookingId));
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
}
