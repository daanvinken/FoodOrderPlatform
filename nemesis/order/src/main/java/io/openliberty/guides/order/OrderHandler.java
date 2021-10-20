package io.openliberty.guides.order;
import java.sql.Connection;
import java.sql.DriverManager;

public class OrderHandler {
    public static void run() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://10.106.117.51:5432/postgresdb",
                            "admin", "test123");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}
