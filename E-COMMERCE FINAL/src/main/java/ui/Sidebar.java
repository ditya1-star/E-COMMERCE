package ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Sidebar extends JPanel {
    public Sidebar(MainFrame owner) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        addButton("Home", "/images/icon_home.png", e -> owner.showPanel("products"));
        addButton("Products", "/images/icon_products.png", e -> owner.showPanel("products"));
        addButton("Cart", "/images/icon_cart.png", e -> owner.showPanel("cart"));
        addButton("Orders", "/images/icon_orders.png", e -> owner.showPanel("orders"));
        addButton("Admin", "/images/icon_admin.png", e -> owner.showPanel("admin"));
    }

    private void addButton(String title, String iconPath, java.awt.event.ActionListener al) {
        JButton b = new JButton(title);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        b.setToolTipText("Open " + title);
        // load icon resource if present
        URL u = getClass().getResource(iconPath);
        if (u != null) b.setIcon(new ImageIcon(u));
        b.addActionListener(al);
        add(b); add(Box.createRigidArea(new Dimension(0,6)));
    }
}
