package com.banking;

import java.util.ArrayList;
import java.util.Date;

public class Service {
    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Booking> bookings = new ArrayList<>();

    void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        try {
            for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
            // Update room attributes
                room.setRoomType(roomType);
                room.setRoomPricePerNight(roomPricePerNight);
                System.out.println("Room updated successfully.");
                return;
        }
    }
            Room newRoom = new Room(roomNumber, roomType, roomPricePerNight);
            rooms.add(newRoom);
        } catch (Exception e) {
            System.out.println("Error in setRoom: " + e.getMessage());
        }
    }

    void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
        try {
            Date normCheckIn = normalizeDate(checkIn);
            Date normCheckOut = normalizeDate(checkOut);

            if (!normCheckIn.before(normCheckOut)) {
                System.out.println("Invalid dates: check-in must be before check-out.");
                return;
            }

            User user = findUserById(userId);
            Room room = findRoomByNumber(roomNumber);

            if (user == null) {
                System.out.println("User not found.");
                return;
            }
            if (room == null) {
                System.out.println("Room not found.");
                return;
            }

            if (!isRoomAvailable(roomNumber, normCheckIn, normCheckOut)) {
                System.out.println("Room not available for the selected period.");
                return;
            }

            long diff = normCheckOut.getTime() - normCheckIn.getTime();
            long nights = Math.max(diff / (1000 * 60 * 60 * 24), 1);
            int totalPrice = (int) (nights * room.getRoomPriceperNight());

            if (user.getBalance() < totalPrice) {
                System.out.println("Insufficient balance.");
                return;
            }

            user.setBalance(user.getBalance() - totalPrice);
            bookings.add(new Booking(user, room, normCheckIn, normCheckOut));
            System.out.println("Booking successful!");
        } catch (Exception e) {
            System.out.println("Error during booking: " + e.getMessage());
        }
    }

    void printAll() {
        try {
            System.out.println("Rooms (latest to oldest):");
            for (int i = rooms.size() - 1; i >= 0; i--) {
                Room room = rooms.get(i);
                System.out.println("Room Number: " + room.roomNumber +", Type: " + room.roomType + ", Price per Night: " + room.roomPriceperNight);
            }

            System.out.println("Bookings (latest to oldest):");
            for (int i = bookings.size() - 1; i >= 0; i--) {
                Booking b = bookings.get(i);
                System.out.println("User ID: " + b.getUser().getUserId() +", Room Number: " + b.getRoom().getRoomNumber() + ", Check-In: " + b.getCheckIn() +", Check-Out: " + b.getCheckOut());
            }
        } catch (Exception e) {
            System.out.println("Error in printAll: " + e.getMessage());
        }
    }

    void setUser(int userId, int balance) {
        try {
            for (User user : users) {
                if (user.userId == userId) {
                    System.out.println("User already exists.");
                    return;
                }
            }
            User newUser = new User(userId, balance);
            users.add(newUser);
        } catch (Exception e) {
            System.out.println("Error in setUser: " + e.getMessage());
        }
    }

    void printAllUsers() {
        try {
            System.out.println("Users (latest to oldest):");
            for (int i = users.size() - 1; i >= 0; i--) {
                User user = users.get(i);
                System.out.println("User ID: " + user.getUserId() + ", Balance: " + user.getBalance());
            }
        } catch (Exception e) {
            System.out.println("Error in printAllUsers: " + e.getMessage());
        }
    }

    private User findUserById(int userId) {
        for (User u : users) {
            if (u.getUserId() == userId) {
                return u;
            }
        }
        return null;
    }

    private Room findRoomByNumber(int roomNumber) {
        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber) {
                return r;
            }
        }
        return null;
    }

    private boolean isRoomAvailable(int roomNumber, Date checkIn, Date checkOut) {
        for (Booking b : bookings) {
            Date bCheckIn = normalizeDate(b.getCheckIn());
            Date bCheckOut = normalizeDate(b.getCheckOut());
            if (b.getRoom().getRoomNumber() == roomNumber && !(checkOut.before(bCheckIn) || checkIn.after(bCheckOut))) {
                return false;
            }
        }
        return true;
    }

    private Date normalizeDate(Date date) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(date);
        cal.set(java.util.Calendar.HOUR_OF_DAY, 0);
        cal.set(java.util.Calendar.MINUTE, 0);
        cal.set(java.util.Calendar.SECOND, 0);
        cal.set(java.util.Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}