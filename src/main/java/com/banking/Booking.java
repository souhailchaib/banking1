package com.banking;

import java.util.Date;

public class Booking {
    User user;
    Room room;
    Date checkIn;
    Date checkOut;

    public Booking(User user, Room room, Date checkIn, Date checkOut) {
        this.user = user;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}