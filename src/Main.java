package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Szkola_programownia?useSSL=false&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root", "coderslab")) {

            //TODO .gitignor
            //TODO backup bazy danych

            //ZAPIS UŻYTKOWNIKA
//            User user1 = new User("Mateszu3", "mat3@wp.pl", "12234");
//            System.out.println(user1);
//            user1.saveToDB(conn);
//            System.out.println(user1);

            //ODCZYT UŻTKOWNIKA PO ID
//            User user = User.loadUserById(conn, 1);
//            System.out.println(user);

            //ODCZYT WSZYSTKICH UŻYTKOWNIKÓWdhf
//            User[] users = User.loadAllUsers(conn);
//            for (User user:users){
//                System.out.println(user);
//            }


            //POBRANIE DANYCH I UPDATE TEGO UŻYTKOWNIKA
//            User user = User.loadUserById(conn, 3);
//            System.out.println(user);
//            user.setUsername("Radek");
//            user.saveToDB(conn);

            //USUNIECIE UZYTKWNIKA
//            User user = User.loadUserById(conn, 3);
//            System.out.println(user);
//            user.deleteUser(conn);
//            System.out.println(user);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
