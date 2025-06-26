package com.banking;
import java.text.SimpleDateFormat;


public class Main {
    public static void main(String[] args) throws Exception {
        Service service = new Service();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        service.setRoom(1, RoomType.STANDARD_SUITE, 1000);
        service.setRoom(2, RoomType.JUNIOR_SUITE, 2000);
        service.setRoom(3, RoomType.MASTER_SUITE, 3000);

        service.setUser(1, 5000);
        service.setUser(2, 10000);
        
        System.out.println("User 1 booking Room 2 from 30/06/2026 to 07/07/2026:");
        service.bookRoom(1, 2, sdf.parse("30/06/2026"), sdf.parse("07/07/2026"));

        System.out.println("User 1 booking Room 2 from 07/07/2026 to 30/06/2026:");
        service.bookRoom(1, 2, sdf.parse("07/07/2026"), sdf.parse("30/06/2026"));

        System.out.println("User 1 booking Room 1 from 07/07/2026 to 08/07/2026:");
        service.bookRoom(1, 1, sdf.parse("07/07/2026"), sdf.parse("08/07/2026"));

        System.out.println("User 2 booking Room 1 from 07/07/2026 to 09/07/2026:");
        service.bookRoom(2, 1, sdf.parse("07/07/2026"), sdf.parse("09/07/2026"));

        System.out.println("User 2 booking Room 3 from 07/07/2026 to 08/07/2026:");
        service.bookRoom(2, 3, sdf.parse("07/07/2026"), sdf.parse("08/07/2026"));

    
        System.out.println("Updating Room 1 to suite with price 10000:");
    
        service.setRoom(1, RoomType.MASTER_SUITE, 10000);

        service.printAll();
        service.printAllUsers();
    }
}