package idv.bowson.mrrs.model;

public class User {

    private String username;
    private String password;
    private boolean enable;

    public User() {
    }

    public User(String username) {
        this.setUsername(username);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
