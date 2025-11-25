package utils;

import models.Product;

import java.util.HashMap;
import java.util.Map;

public class CartManager {

    private static final Map<Product, Integer> cart = new HashMap<>();

    public static void addToCart(Product p, int qty) {
        cart.put(p, cart.getOrDefault(p, 0) + qty);
    }

    public static Map<Product, Integer> getCart() {
        return cart;
    }

    public static void clearCart() {
        cart.clear();
    }
}
