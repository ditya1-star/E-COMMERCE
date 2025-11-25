package ui;

import utils.CartManager;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class OrderCheckoutFrame extends JFrame {

    public OrderCheckoutFrame() {
        super("Checkout");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTextArea area = new JTextArea();
        area.setEditable(false);

        // Show items
        CartManager.getCart().forEach((p, q) -> {
            BigDecimal total = p.getPrice().multiply(BigDecimal.valueOf(q));
            area.append(p.getName() + " x " + q + " — ₹ " + total + "\n");
        });

        add(new JScrollPane(area), BorderLayout.CENTER);
    }
}
