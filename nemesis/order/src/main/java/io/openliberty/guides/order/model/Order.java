package io.openliberty.guides.order.model;

import java.util.List;

class OrderItem{
    public String name;
    public double price;
    public int id;
    public int quantity;
}

public class Order{
    public int id;
    public int restaurantId;
    public List<OrderItem> OrderItems;
    public double totalAmount;
}