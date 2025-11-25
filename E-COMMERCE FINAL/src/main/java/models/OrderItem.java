package models;

public class OrderItem {
    private Integer productId;
    private int quantity;
    private double price;

    public OrderItem(Integer productId, int quantity, double price) {
        this.productId = productId; this.quantity = quantity; this.price = price;
    }
    public Integer getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
}
