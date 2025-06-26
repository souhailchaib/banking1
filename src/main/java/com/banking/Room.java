package com.banking;

public class Room {
    int roomNumber;
    RoomType roomType;
    int roomPriceperNight;


    public Room(int roomNumber, RoomType roomType, int roomPriceperNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomPriceperNight = roomPriceperNight;
    }

    public int getRoomNumber() {
        return roomNumber;
    }       
    public RoomType getRoomType() {
        return roomType;
    }   
    public int getRoomPriceperNight() {
        return roomPriceperNight;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }           

    public void setRoomPricePerNight(int roomPriceperNight) {
        this.roomPriceperNight = roomPriceperNight;
    }

}
