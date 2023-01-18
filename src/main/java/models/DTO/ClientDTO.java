package models.DTO;

import models.entity.User;

public class ClientDTO {
    private Integer clientID;
    private Integer userID;
    private Integer bonusPoints;
    private Boolean success;
    private String message;

    public ClientDTO() {
    }

    public ClientDTO(Boolean success) {
        this.success = success;
    }

    public ClientDTO(Boolean success, String message) {
        this.success = success;
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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
