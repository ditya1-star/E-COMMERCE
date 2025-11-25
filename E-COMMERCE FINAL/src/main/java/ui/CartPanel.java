package ui;

import ecommerce.App;
import dao.OrderDAO;
import models.OrderItem;
import models.Product;
import utils.AppLogger;
import utils.Toast;
import utils.UndoManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CartPanel extends JPanel {
    private final JTextArea area = new JTextArea();

    public CartPanel() {
        setLayout(new BorderLayout(8,8));
        JLabel title = new JLabel("Cart"); title.setFont(title.getFont().deriveFont(20f));
        add(title, BorderLayout.NORTH);
        area.setEditable(false); add(new JScrollPane(area), BorderLayout.CENTER);

        JPanel south = new JPanel();
        JButton undo = new JButton("Undo Remove"); JButton checkout = new JButton("Checkout");
        south.add(undo); south.add(checkout); add(south, BorderLayout.SOUTH);

        undo.addActionListener(e -> {
            UndoManager um = App.getUndoManager();
            Product p = um.undoLast();
            if (p!=null) Toast.showToast(SwingUtilities.getWindowAncestor(this), "Restored: "+p.getName());
            else Toast.showToast(SwingUtilities.getWindowAncestor(this), "Nothing to undo");
        });

        checkout.addActionListener(e -> {
            // minimal: build orderitems from cart placeholder
            try {
                List<OrderItem> items = new ArrayList<>();
                double total = 0;
                // In this minimal code cart is not implemented persistently; placeholder log:
                AppLogger.log("Checkout initiated (placeholder).");
                Toast.showToast(SwingUtilities.getWindowAncestor(this), "Checkout (placeholder)");
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Checkout error: "+ex.getMessage()); }
        });
    }

    public void refresh() {
        area.setText("Cart contents placeholder.\nImplement persistent cart for full behavior.");
    }
}
