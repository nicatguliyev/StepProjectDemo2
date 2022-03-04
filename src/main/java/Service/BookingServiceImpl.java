package Service;

import Model.Booking;

import java.util.ArrayList;

public interface BookingServiceImpl {
    void createBooking(int passengerId, String serial_number);
    int getPassengerId(int PassengerId, String serialnumber);
    void cancelBooking(int bookingId);
}
