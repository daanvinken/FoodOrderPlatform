package io.openliberty.guides.order.model;

import javax.json.Json;
import java.util.List;

class OrderItem{
    public String name;
    public double price;
    public int id;
    public int quantity;
}

public class Order {
    public int restaurantId;
    public Json OrderItems;
    public double totalAmount;
    public int userId;

    public double getTotalAmount() {
        return this.totalAmount;
    }


}
