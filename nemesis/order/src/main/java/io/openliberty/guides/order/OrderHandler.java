package io.openliberty.guides.order;
import io.openliberty.guides.order.model.Order;

import javax.validation.constraints.Null;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrderHandler {
    public static void insertOrderDB(Order order) {
        Connection c = null;
        String query = "INSERT INTO orders(restaurantId, orderItems, orderAmount, userId) VALUES " +
                "('"+order.restaurantId+"', '"+order.OrderItems+"', '"+order.totalAmount+"', '"+order.userId+"');";

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://10.106.117.51:5432/nemesis",
                            "admin", "test123");

            System.out.println("Connected to database!");

            // TODO Switch to prepared statement
            Statement statement = c.createStatement();
            statement.executeUpdate(query);

            System.out.println("Successfully inserted order into database!");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

    }
}
