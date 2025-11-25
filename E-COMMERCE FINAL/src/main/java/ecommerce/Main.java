package ecommerce;

import ui.SplashScreen;
import utils.ThemeManager;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // init theme (light default)
        ThemeManager.init(false);
        SwingUtilities.invokeLater(() -> {
            new SplashScreen().showSplashAndStart();
        });
    }
}
