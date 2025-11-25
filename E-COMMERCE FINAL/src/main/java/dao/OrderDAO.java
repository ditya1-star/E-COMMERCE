package dao;

import db.DBConnection;
import models.Order;
import models.OrderItem;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public int createOrder(int userId, double total, List<OrderItem> items) throws SQLException {
        String insOrder = "INSERT INTO orders(user_id,total_amount,status) VALUES (?,?,?)";
        String insItem = "INSERT INTO order_items(order_id,product_id,quantity,price) VALUES (?,?,?,?)";
        try (Connection c = DBConnection.getConnection()) {
            c.setAutoCommit(false);
            try (PreparedStatement psO = c.prepareStatement(insOrder, Statement.RETURN_GENERATED_KEYS)) {
                psO.setInt(1, userId); psO.setDouble(2, total); psO.setString(3, "PROCESSING");
                psO.executeUpdate();
                int orderId;
                try (ResultSet rs = psO.getGeneratedKeys()) { rs.next(); orderId = rs.getInt(1); }
                try (PreparedStatement psI = c.prepareStatement(insItem)) {
                    for (OrderItem it : items) {
                        psI.setInt(1, orderId);
                        psI.setInt(2, it.getProductId());
                        psI.setInt(3, it.getQuantity());
                        psI.setDouble(4, it.getPrice());
                        psI.addBatch();
                    }
                    psI.executeBatch();
                }
                c.commit(); return orderId;
            } catch (SQLException ex) { c.rollback(); throw ex; } finally { c.setAutoCommit(true); }
        }
    }

    public List<Order> getOrdersByUser(int userId) throws SQLException {
        List<Order> out = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id=? ORDER BY created_at DESC";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order o = new Order(rs.getInt("id"), rs.getInt("user_id"), rs.getDouble("total_amount"),
                            rs.getString("status"), rs.getTimestamp("created_at").toLocalDateTime());
                    out.add(o);
                }
            }
        }
        return out;
    }
}
