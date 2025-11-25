package models;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Integer id;
    private Integer userId;
    private double total;
    private String status;
    private LocalDateTime createdAt;
    private List<OrderItem> items;

    public Order() {}
    public Order(Integer id, Integer userId, double total, String status, LocalDateTime createdAt) {
        this.id = id; this.userId = userId; this.total = total; this.status = status; this.createdAt = createdAt;
    }

    public Integer getId() { return id; }
    public Integer getUserId() { return userId; }
    public double getTotal() { return total; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setItems(List<OrderItem> items) { this.items = items; }
    public List<OrderItem> getItems() { return items; }
}
