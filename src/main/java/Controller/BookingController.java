package Controller;

import Service.BookingService;

import java.util.Scanner;

public class BookingController {

      private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

     public void cancelBooking(){
         System.out.print("Enter Booking id for cancel : ");
         Scanner scanner = new Scanner(System.in);
         int bookingId = scanner.nextInt();
         bookingService.cancelBooking(bookingId);
     }
}
