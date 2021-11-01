package com.entwik.carromcash.models.local;

public class LocalDataModel {

    private String id;
    private String mobileNumber;
    private String profilePic;
    private String level;
    private String token;
    private String winningBalance;
    private String depositBalance;
    private String bonusBalance;

    public LocalDataModel(String id,
                          String mobileNumber,
                          String profilePic,
                          String level,
                          String token,
                          String winningBalance,
                          String depositBalance,
                          String bonusBalance) {
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.profilePic = profilePic;
        this.level = level;
        this.token = token;
        this.winningBalance = winningBalance;
        this.depositBalance = depositBalance;
        this.bonusBalance = bonusBalance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getWinningBalance() {
        return winningBalance;
    }

    public void setWinningBalance(String winningBalance) {
        this.winningBalance = winningBalance;
    }

    public String getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(String depositBalance) {
        this.depositBalance = depositBalance;
    }

    public String getBonusBalance() {
        return bonusBalance;
    }

    public void setBonusBalance(String bonusBalance) {
        this.bonusBalance = bonusBalance;
    }
}
