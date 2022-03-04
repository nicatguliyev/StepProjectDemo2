package Service;

import Dao.BookingDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingService implements BookingServiceImpl {

    private final BookingDao bookingDao;

    public BookingService(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Override
    public void createBooking(int passengerId, String serial_number) {
        if (!bookingDao.createBooking(passengerId, serial_number)) {
            System.out.println("Booking is created");
        } else {
            System.out.println("Booking is not created");
        }
    }

    @Override
    public int getPassengerId(int passengerId, String serialnumber) {
        ResultSet resultSet = bookingDao.getPassengerId(passengerId, serialnumber);
        int count = 0;
        try {
            while (resultSet.next()) {
                count  = resultSet.getInt("count");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
         return count;
    }

    @Override
    public void cancelBooking(int bookingId) {
        if(bookingDao.cancelBooking(bookingId)){
            System.out.println("Booking is cancelled");
        }
        else{
            System.out.println("Booking is not cancelled");
        }
    }


}
