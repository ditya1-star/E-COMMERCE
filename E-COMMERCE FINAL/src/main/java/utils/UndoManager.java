package utils;

import models.Product;

import java.util.Stack;

public class UndoManager {
    private final Stack<Product> stack = new Stack<>();

    public void pushRemoved(Product p) { stack.push(p); AppLogger.log("Undo push: "+p.getName()); }
    public Product undoLast() { if (stack.isEmpty()) return null; Product p = stack.pop(); AppLogger.log("Undo pop: "+p.getName()); return p; }
}
