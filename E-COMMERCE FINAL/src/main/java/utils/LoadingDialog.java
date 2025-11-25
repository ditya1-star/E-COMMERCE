package utils;

import javax.swing.*;
import java.awt.*;

public class LoadingDialog {
    private final JDialog dlg;
    public LoadingDialog(Frame owner, String msg) {
        dlg = new JDialog(owner, true);
        JPanel p = new JPanel(new BorderLayout(8,8));
        p.add(new JLabel(msg), BorderLayout.NORTH);
        JProgressBar pb = new JProgressBar(); pb.setIndeterminate(true);
        p.add(pb, BorderLayout.CENTER);
        dlg.getContentPane().add(p);
        dlg.pack(); dlg.setLocationRelativeTo(owner);
    }
    public void show() { SwingUtilities.invokeLater(() -> dlg.setVisible(true)); }
    public void close() { SwingUtilities.invokeLater(() -> dlg.dispose()); }
}
