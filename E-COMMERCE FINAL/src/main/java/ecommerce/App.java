package ecommerce;

import models.User;
import utils.AppLogger;
import utils.ThemeManager;
import utils.UndoManager;

/**
 * Simple global context
 */
public class App {
    private static User currentUser;
    private static final UndoManager undoManager = new UndoManager();

    public static User getCurrentUser() { return currentUser; }
    public static void setCurrentUser(User u) { currentUser = u; if (u!=null) AppLogger.log("User logged in: "+u.getUsername()); }
    public static UndoManager getUndoManager() { return undoManager; }
}
