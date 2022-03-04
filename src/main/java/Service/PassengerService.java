package Service;

import Dao.PassengerDao;
import Model.Passenger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PassengerService implements PassengerServiceImpl {

    private PassengerDao passengerDao;

    public PassengerService(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    @Override
    public void insertPassenger(String firstname, String lastname, String finCode) {
        if (!passengerDao.insertPassenger(firstname, lastname, finCode)) {
            System.out.println("Passenger is created");
        } else {
            System.out.println("passenger is not created");
        }

    }

    @Override
    public ArrayList<Integer> getLastPassengers(short seats) {
        ResultSet resultSet = passengerDao.getLastPassengers(seats);
        ArrayList<Integer> ids = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                ids.add(id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ids;
    }

    @Override
    public ArrayList<Passenger> getAllPassengers() {
        ResultSet resultSet = passengerDao.getAllPassengers();
        ArrayList<Passenger> passengers = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Passenger passenger = new Passenger(resultSet.getInt("id"), resultSet.getString("fin_code"),
                        resultSet.getString("firstname"), resultSet.getString("lastname"));
                passengers.add(passenger);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return passengers;
    }

    @Override
    public void showAllBookings(String finCode) {
        ResultSet resultSet = passengerDao.showAllBookings(finCode);
        System.out.println("FINCODE          FIRSTNAME           LASTNAME          BOOKINGID           SERIAL NUMBER           FROM            DESTINATION            DATE");
        System.out.println("-------         ----------          ----------        -----------         ----------------       --------        --------------          --------");
        try {
            while (resultSet.next()){
                System.out.println(resultSet.getString("fin_code") + "          " + resultSet.getString("firstname") + "              " +
                        resultSet.getString("lastname") + "               " + resultSet.getInt("booking_id") + "                   " +
                        resultSet.getString("serial_number") + "              "  + resultSet.getString("from") + "            " +
                        resultSet.getString("destination") + "             " + resultSet.getDate("date"));
            }
        }
        catch (SQLException e){
            System.out.println( e.getMessage());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
