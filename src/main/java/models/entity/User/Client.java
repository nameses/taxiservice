package models.entity.User;

import java.util.Objects;

public class Client {
    private Integer clientID;
    private Integer userID;
    private User user;
    private Integer bonusPoints;
    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(Integer bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(clientID, client.clientID) && Objects.equals(userID, client.userID) && Objects.equals(user, client.user) && Objects.equals(bonusPoints, client.bonusPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientID, userID, user, bonusPoints);
    }
}
