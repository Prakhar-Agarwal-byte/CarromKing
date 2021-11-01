package com.entwik.carromcash.models.wallet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WalletResponseDataModel {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("userId")
    @Expose
    private int userId;
    @SerializedName("winningBalance")
    @Expose
    private int winningBalance;
    @SerializedName("depositBalance")
    @Expose
    private int depositBalance;
    @SerializedName("bonusBalance")
    @Expose
    private int bonusBalance;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("totalBalance")
    @Expose
    private int totalBalance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWinningBalance() {
        return winningBalance;
    }

    public void setWinningBalance(int winningBalance) {
        this.winningBalance = winningBalance;
    }

    public int getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(int depositBalance) {
        this.depositBalance = depositBalance;
    }

    public int getBonusBalance() {
        return bonusBalance;
    }

    public void setBonusBalance(int bonusBalance) {
        this.bonusBalance = bonusBalance;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(int totalBalance) {
        this.totalBalance = totalBalance;
    }
}
