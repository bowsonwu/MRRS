package idv.bowson.mrrs.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String password;
    private String name;
    private boolean enabled;
    private List<Role> roles;

    public User() {
        roles = new ArrayList<Role>();
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
