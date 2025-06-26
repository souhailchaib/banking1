package com.banking;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();

        service.setUser(1,1000);
        service.setRoom(101, RoomType.STANDARD_SUITE, 100);

        Date checkIn = new Date();
        Date checkOut = new Date(checkIn.getTime() + 2 * 24 * 60 * 60 * 1000); // 2 days later

        service.Booking(1, 101, checkIn, checkOut);

        service.printAll();
        service.printAllUsers();
    }
}