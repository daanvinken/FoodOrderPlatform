package io.openliberty.guides.order.model;

import com.google.gson.Gson;
import com.google.gson.*;
import java.time.LocalDateTime;

class OrderItem{
    public String name;
    public double price;
    public int id;
    public int quantity;
}

public class Order {
    public int orderId;
    public int restaurantId;
    public String OrderItems;
    public double totalAmount;
    public int userId;
    public String status = "created";
    public LocalDateTime createdAt = LocalDateTime.now();
    public LocalDateTime updatedAt = LocalDateTime.now();

    public Order(int restaurantId, String orderItems, double totalAmount, int userId, int orderId, String status) {
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.OrderItems = orderItems;
        this.totalAmount = totalAmount;
        this.userId = userId;
        this.status = status;
    }

    public void updateAtCreation() {
        this.status = "CREATED";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", restaurantId=" + restaurantId +
                ", OrderItems=" + OrderItems +
                ", totalAmount=" + totalAmount +
                ", userId=" + userId +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRepr = gson.toJson(this);
        return jsonRepr;
    }
}
