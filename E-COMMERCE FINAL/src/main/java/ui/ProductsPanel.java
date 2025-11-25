package ui;

import dao.ProductDAO;
import models.Product;
import utils.CartManager;
import utils.Toast;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;

public class ProductsPanel extends JPanel {
    private final JPanel grid = new JPanel();
    private final JComboBox<String> sortCombo = new JComboBox<>(new String[]{"Default","Price: Low→High","Price: High→Low","Name A→Z"});
    private final ProductDAO dao = new ProductDAO();

    public ProductsPanel() {
        setLayout(new BorderLayout(8,8));

        JPanel top = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Products");
        title.setFont(title.getFont().deriveFont(20f));
        top.add(title, BorderLayout.WEST);

        sortCombo.setToolTipText("Sort products");
        top.add(sortCombo, BorderLayout.EAST);
        add(top, BorderLayout.NORTH);

        grid.setLayout(new WrapLayout(FlowLayout.LEFT,12,12));
        add(new JScrollPane(grid), BorderLayout.CENTER);

        sortCombo.addActionListener(e -> refresh());
        refresh();
    }

    public void refresh() {
        grid.removeAll();
        try {
            List<Product> list = dao.findAll();
            String s = (String) sortCombo.getSelectedItem();

            if ("Price: Low→High".equals(s)) list.sort(Comparator.comparing(Product::getPrice));
            else if ("Price: High→Low".equals(s)) list.sort(Comparator.comparing(Product::getPrice).reversed());
            else if ("Name A→Z".equals(s)) list.sort(Comparator.comparing(Product::getName));

            for (Product p : list) grid.add(cardFor(p));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Load error: " + ex.getMessage());
        }

        revalidate();
        repaint();
    }

    /** PRODUCT CARD WITH ADD + BUY BUTTONS */
    private JPanel cardFor(Product p) {
        JPanel card = new JPanel(new BorderLayout(6,6));
        card.setPreferredSize(new Dimension(240,180));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(6,6,6,6)
        ));

        JLabel name = new JLabel("<html><b>"+p.getName()+"</b></html>");
        JLabel cat = new JLabel(p.getCategory());
        JLabel price = new JLabel("₹ " + p.getPrice());
        JLabel stock = new JLabel("Stock: " + p.getStock());

        if (p.getStock() < 5) stock.setForeground(Color.RED);

        JSpinner qty = new JSpinner(
                new SpinnerNumberModel(1, 1, Math.max(1, p.getStock()), 1)
        );

        JButton add = new JButton("Add to Cart");
        JButton buy = new JButton("Buy Now");

        // ADD TO CART BUTTON LOGIC
        add.addActionListener(e -> {
            int q = (int) qty.getValue();
            CartManager.addToCart(p, q);
            Toast.showToast(SwingUtilities.getWindowAncestor(this), p.getName() + " added to cart");
        });

        // BUY NOW BUTTON LOGIC
        buy.addActionListener(e -> {
            int q = (int) qty.getValue();
            CartManager.clearCart();
            CartManager.addToCart(p, q);
            new ui.OrderCheckoutFrame().setVisible(true);
        });

        JPanel top = new JPanel(new BorderLayout());
        top.add(name, BorderLayout.NORTH);
        top.add(cat, BorderLayout.SOUTH);

        JPanel mid = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mid.add(price);
        mid.add(new JLabel("Qty:"));
        mid.add(qty);

        JPanel bottom = new JPanel(new GridLayout(1,2,5,0));
        bottom.add(add);
        bottom.add(buy);

        card.add(top, BorderLayout.NORTH);
        card.add(stock, BorderLayout.EAST);
        card.add(mid, BorderLayout.CENTER);
        card.add(bottom, BorderLayout.SOUTH);

        return card;
    }
}
