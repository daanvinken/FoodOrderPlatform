package io.openliberty.guides.order;
import io.openliberty.guides.order.model.Order;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DBHandler {
    static volatile Connection conn;
    DBHandler() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager
                    .getConnection("jdbc:postgresql://10.106.117.51:5432/nemesis",
                            "admin", "test123");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

    public static int insertOrderDB(Order order) {
        int order_id = -1;
        String query = "INSERT INTO orders(restaurantId, " +
                                            "totalAmount, " +
                                            "userId, " +
                                            "status, " +
                                            "createdAt, " +
                                            "updatedAt) " +
                "VALUES (?,?,?,?,?,?);";

        try {
            PreparedStatement insertOrder = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            insertOrder.setInt(1, order.restaurantId);
            insertOrder.setDouble(2, order.totalAmount);
            insertOrder.setInt(3, order.userId);
            insertOrder.setString(4, order.status);
            insertOrder.setObject(5, order.createdAt, 93);
            insertOrder.setObject(6, order.updatedAt, 93);

            // Get generated order id
            ResultSet rs = insertOrder.getGeneratedKeys();
            if (rs.next()) {
                order_id = rs.getInt(1);
                System.out.println("Successfully inserted order into database!");
            }
            else {
                throw new SQLException("Failed to create new order.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return order_id;
    }

    public static Order FetchOrderDB(int order_id)
    {
        Order orderToCreate = null;
        try
        {
            String query = "SELECT * FROM orders WHERE orderId='"+order_id+"';";
            Statement statement = conn.createStatement();
            ResultSet rs =  statement.executeQuery(query);

            if(rs.next()) {
                int orderId = rs.getInt("orderId");
                int restaurantId = rs.getInt("restaurantId");
                double orderAmount = rs.getDouble("orderAmount");
                int userId = rs.getInt("userId");
                String status = rs.getString("status");
                orderToCreate = new Order(restaurantId, null, orderAmount, userId, orderId, status);
            }
            else {
                throw new SQLException("No orders found for this orderId");
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Error in fetching user from DB: \n" + ex.getMessage());
        }
        return orderToCreate;
    }
}
