package models;

import java.math.BigDecimal;

public class Product {
    private Integer id;
    private String name;
    private String category;
    private BigDecimal price;
    private int stock;

    public Product() {}
    public Product(Integer id, String name, String category, BigDecimal price, int stock) {
        this.id = id; this.name = name; this.category = category; this.price = price; this.stock = stock;
    }
    public Product(String name, String category, BigDecimal price, int stock) {
        this(null, name, category, price, stock);
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public BigDecimal getPrice() { return price; }
    public int getStock() { return stock; }

    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
}
