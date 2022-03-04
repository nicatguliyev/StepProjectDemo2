package Dao;

import util.SqlConnection;

import java.sql.*;


public class FlightDao implements FlightDaoImpl {
    Connection connection = SqlConnection.createConnection();

    @Override
    public ResultSet showAllFlights() {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("Select * from Flights");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public ResultSet showFlightBySerial(String serial) {
        ResultSet resultSet = null;

        return null;
    }

    @Override
    public boolean updateSeats(String serialNumber, short newSeats) {
        boolean bool = false;
        try {
            Statement statement = connection.createStatement();
            bool = statement.execute(String.format("Update Flights set seats = '%d' where serial_number = '%s'", newSeats, serialNumber));
            return bool;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ResultSet getFlightSeatCount(String serialNumber) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("Select seats from Flights");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

}
