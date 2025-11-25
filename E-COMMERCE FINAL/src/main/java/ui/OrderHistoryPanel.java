package ui;

import ecommerce.App;
import dao.OrderDAO;
import models.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrderHistoryPanel extends JPanel {
    private final JTextArea area = new JTextArea();

    public OrderHistoryPanel() {
        setLayout(new BorderLayout(8,8));
        JLabel title = new JLabel("Orders"); title.setFont(title.getFont().deriveFont(20f));
        add(title, BorderLayout.NORTH);
        area.setEditable(false); add(new JScrollPane(area), BorderLayout.CENTER);
        refresh();
    }

    public void refresh() {
        area.setText("");
        try {
            if (App.getCurrentUser() == null) { area.setText("Login to view orders"); return; }
            OrderDAO od = new OrderDAO();
            List<Order> list = od.getOrdersByUser(App.getCurrentUser().getId());
            for (Order o : list) {
                area.append("Order #" + o.getId() + " | Total: " + o.getTotal() + " | " + o.getCreatedAt() + "\n");
            }
        } catch (Exception ex) { area.setText("Error: " + ex.getMessage()); }
    }
}
