package ui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {

    private final JPanel content = new JPanel(new BorderLayout());

    // FIX: inline initialization so it's effectively final
    private final Map<String, JPanel> pages = new HashMap<String, JPanel>() {{
        put("products", new ProductsPanel());
        put("cart", new CartPanel());
        put("admin", new AdminPanel());
        put("orders", new OrderHistoryPanel());
    }};

    public MainFrame() {
        super("MyShop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100,700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        TopBar top = new TopBar(this);
        add(top, BorderLayout.NORTH);

        Sidebar side = new Sidebar(this);
        side.setPreferredSize(new Dimension(180, getHeight()));
        add(side, BorderLayout.WEST);

        add(content, BorderLayout.CENTER);
        showPanel("products");
    }

    public void showPanel(String key) {
        content.removeAll();
        JPanel p = pages.getOrDefault(key, new JPanel());

        content.add(p, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();

        // animation
        p.setVisible(false);
        final int[] step = {0};

        Timer t = new Timer(20, null);
        t.addActionListener(e -> {
            step[0]++;
            if (step[0] > 3) {
                p.setVisible(true);
                ((Timer)e.getSource()).stop();
            }
        });
        t.start();
    }
}
