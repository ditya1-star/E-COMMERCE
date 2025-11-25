package utils;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class ThemeManager {
    private static boolean dark = false;
    public static void init(boolean startDark) {
        dark = startDark; apply();
    }
    public static void toggle() { dark = !dark; apply(); }
    private static void apply() {
        try {
            if (dark) UIManager.setLookAndFeel(new FlatDarkLaf());
            else UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) { System.err.println("Theme error: " + e.getMessage()); }
    }
    public static boolean isDark() { return dark; }
}
