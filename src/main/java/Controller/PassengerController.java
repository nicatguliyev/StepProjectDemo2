package Controller;

import Service.PassengerService;

import java.awt.*;
import java.util.Scanner;

public class PassengerController {
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    public void showAllBookings(){
        System.out.print("Enter FIN code : ");
        Scanner scanner = new Scanner(System.in);
        String finCode = scanner.nextLine();
        passengerService.showAllBookings(finCode);
    }
}
