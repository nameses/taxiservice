package models.view;

public class ClientView {
    private Integer clientID;
    private Integer userID;
    private Integer bonusPoints;

    public ClientView(Integer clientID, Integer userID, Integer bonusPoints) {
        this.clientID = clientID;
        this.userID = userID;
        this.bonusPoints = bonusPoints;
    }

    public ClientView() {
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(Integer bonusPoints) {
        this.bonusPoints = bonusPoints;
    }
}
