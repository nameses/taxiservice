package entity;

import java.util.Objects;

public class UserAccount {
    private Integer userID;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String role;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount userAccount = (UserAccount) o;
        return Objects.equals(userID, userAccount.userID) && Objects.equals(firstname, userAccount.firstname) && Objects.equals(lastname, userAccount.lastname) && Objects.equals(username, userAccount.username) && Objects.equals(password, userAccount.password) && Objects.equals(phone, userAccount.phone) && Objects.equals(email, userAccount.email) && Objects.equals(role, userAccount.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, firstname, lastname, username, password, phone, email, role);
    }
}
