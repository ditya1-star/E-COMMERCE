package ui;

import dao.ProductDAO;
import models.Product;
import utils.Toast;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminPanel extends JPanel {
    private final ProductDAO dao = new ProductDAO();
    private final DefaultTableModel model = new DefaultTableModel(new Object[]{"ID","Name","Category","Price","Stock"},0);
    private final JTable table = new JTable(model);

    public AdminPanel() {
        setLayout(new BorderLayout(8,8));
        JLabel title = new JLabel("Admin"); title.setFont(title.getFont().deriveFont(20f));
        add(title, BorderLayout.NORTH);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel south = new JPanel();
        JButton add = new JButton("Add"); JButton edit = new JButton("Edit"); JButton del = new JButton("Delete");
        south.add(add); south.add(edit); south.add(del);
        add(south, BorderLayout.SOUTH);

        add.addActionListener(e -> showAddDialog());
        edit.addActionListener(e -> showEditDialog());
        del.addActionListener(e -> doDelete());
        refresh();
    }

    private void refresh() {
        model.setRowCount(0);
        try {
            List<Product> list = dao.findAll();
            for (Product p : list) model.addRow(new Object[]{p.getId(), p.getName(), p.getCategory(), p.getPrice(), p.getStock()});
        } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Load error: "+ex.getMessage()); }
    }

    private void showAddDialog() {
        JTextField name = new JTextField(), cat = new JTextField(), price = new JTextField(), stock = new JTextField();
        Object[] fields = {"Name", name, "Category", cat, "Price", price, "Stock", stock};
        int ok = JOptionPane.showConfirmDialog(this, fields, "Add Product", JOptionPane.OK_CANCEL_OPTION);
        if (ok==JOptionPane.OK_OPTION) {
            try {
                Product p = new Product(name.getText(), cat.getText(), new java.math.BigDecimal(price.getText()), Integer.parseInt(stock.getText()));
                dao.save(p);
                Toast.showToast(SwingUtilities.getWindowAncestor(this), "Product saved");
                refresh();
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Save error: "+ex.getMessage()); }
        }
    }

    private void showEditDialog() {
        int r = table.getSelectedRow();
        if (r<0) { JOptionPane.showMessageDialog(this, "Select row"); return; }
        int id = (int) model.getValueAt(r,0);
        String name = (String) model.getValueAt(r,1);
        String cat = (String) model.getValueAt(r,2);
        String price = model.getValueAt(r,3).toString();
        String stock = model.getValueAt(r,4).toString();
        JTextField tname = new JTextField(name), tcat = new JTextField(cat), tprice = new JTextField(price), tstock = new JTextField(stock);
        Object[] fields = {"Name", tname, "Category", tcat, "Price", tprice, "Stock", tstock};
        int ok = JOptionPane.showConfirmDialog(this, fields, "Edit Product", JOptionPane.OK_CANCEL_OPTION);
        if (ok==JOptionPane.OK_OPTION) {
            try {
                Product p = new Product(Integer.valueOf(id), tname.getText(), tcat.getText(), new java.math.BigDecimal(tprice.getText()), Integer.parseInt(tstock.getText()));
                dao.save(p);
                Toast.showToast(SwingUtilities.getWindowAncestor(this), "Product updated");
                refresh();
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Update error: "+ex.getMessage()); }
        }
    }

    private void doDelete() {
        int r = table.getSelectedRow(); if (r<0) { JOptionPane.showMessageDialog(this,"Select row"); return; }
        int id = (int) model.getValueAt(r,0);
        try { dao.delete(id); Toast.showToast(SwingUtilities.getWindowAncestor(this), "Deleted"); refresh(); }
        catch (Exception ex) { JOptionPane.showMessageDialog(this, "Delete error: "+ex.getMessage()); }
    }
}
