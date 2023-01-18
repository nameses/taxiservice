package models.view;

import models.entity.enums.UserRole;

public class UserView {
    private Integer userID;
    private String username;
    private String fullname;
    private String phone;
    private String email;
    private UserRole role;

    public UserView(Integer userID, String username, String fullname,
                    String phone, String email, UserRole role) {
        this.userID = userID;
        this.username = username;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.role = role;
    }

    public UserView() {
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
