package models;

public class Customer extends User {
    public Customer(Integer id, String username, String email) { super(id, username, email); }
    @Override public String role() { return "CUSTOMER"; }
}
