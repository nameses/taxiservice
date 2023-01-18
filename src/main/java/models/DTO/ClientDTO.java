package models.DTO;

import models.entity.User;

public class ClientDTO {
    private Integer clientID;
    private Integer userID;
    private Integer bonusPoints;
    private Boolean status;
    private String message;

    public ClientDTO() {
    }

    public ClientDTO(Boolean status) {
        this.status = status;
    }

    public ClientDTO(Boolean status, String message) {
        this.status = status;
        this.message = message;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
