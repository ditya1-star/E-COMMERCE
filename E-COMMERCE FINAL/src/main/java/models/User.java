package models;

public abstract class User {
    protected Integer id;
    protected String username;
    protected String email;

    public User(Integer id, String username, String email) {
        this.id = id; this.username = username; this.email = email;
    }
    public Integer getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public abstract String role();
}
