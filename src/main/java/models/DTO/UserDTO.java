package models.DTO;

import models.entity.enums.UserRole;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private Integer userID;
    private String username;
    private String password;
    private String fullname;
    private String phone;
    private String email;
    private UserRole role;
    private ArrayList<String> messages;
    private Boolean status;


    public UserDTO() {
    }

    public UserDTO(Boolean status) {
        this.status = status;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void addMessages(String message){
        if(messages==null) messages = new ArrayList<>();
        this.messages.add(message);
    }
    public ArrayList<String> getMessages() {
        return messages;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
