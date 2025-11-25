package dao;

import db.DBConnection;
import models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public Product save(Product p) throws SQLException {
        String sql = (p.getId()==null)
                ? "INSERT INTO products(name,category,price,stock) VALUES (?,?,?,?)"
                : "UPDATE products SET name=?,category=?,price=?,stock=? WHERE id=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getCategory());
            ps.setBigDecimal(3, p.getPrice());
            ps.setInt(4, p.getStock());
            if (p.getId() != null) ps.setInt(5, p.getId());
            ps.executeUpdate();
            if (p.getId()==null) try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) p.setId(rs.getInt(1)); }
            return p;
        }
    }

    public List<Product> findAll() throws SQLException {
        List<Product> out = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection c = DBConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) out.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getString("category"), rs.getBigDecimal("price"), rs.getInt("stock")));
        }
        return out;
    }

    public boolean delete(int id) throws SQLException {
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM products WHERE id=?")) {
            ps.setInt(1, id); return ps.executeUpdate() > 0;
        }
    }
}
