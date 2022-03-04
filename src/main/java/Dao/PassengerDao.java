package Dao;

import Model.Passenger;
import util.SqlConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PassengerDao implements PassengerDaoImpl {
    Connection connection = SqlConnection.createConnection();


    @Override
    public boolean insertPassenger(String firstname, String lastname, String finCode) {
        Boolean bool = false;
        try {
            Statement statement = connection.createStatement();
            bool = statement.execute(String.format("Insert Into Passengers (firstname, lastname, fin_code) values('%s' , '%s' , '%s')",
                    firstname, lastname, finCode));
            return bool;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return true;
        }

    }

    @Override
    public ResultSet getLastPassengers(short seats) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select id  from Passengers order by id desc limit " + seats);
            return resultSet;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public ResultSet getAllPassengers() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from Passengers");
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public ResultSet getPassengersByFin(String finCode) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("Select * from Passengers where fin_code = '%s'", finCode));
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet showAllBookings(String finCode) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("Select p.fin_code, p.firstname, p.lastname, b.id as booking_id, b.serial_number," +
                    " f.\"from\", f.destination, f.\"date\"\n" +
                    "  from Passengers p join  Booking b on p.id = b.passenger_id join Flights f on b.serial_number = f.serial_number\n" +
                    "where p.fin_code = '%s'", finCode));
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
