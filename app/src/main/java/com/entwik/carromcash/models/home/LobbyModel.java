package com.entwik.carromcash.models.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LobbyModel {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("gameId")
    @Expose
    private int gameId;
    @SerializedName("entryFee")
    @Expose
    private int entryFee;
    @SerializedName("rakePercent")
    @Expose
    private int rakePercent;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("maxPlayers")
    @Expose
    private int maxPlayers;
    @SerializedName("expiresAt")
    @Expose
    private String expiresAt;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("isLocked")
    @Expose
    private boolean isLocked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(int entryFee) {
        this.entryFee = entryFee;
    }

    public int getRakePercent() {
        return rakePercent;
    }

    public void setRakePercent(int rakePercent) {
        this.rakePercent = rakePercent;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public boolean isIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    @Override
    public String toString() {
        return "LobbyModel{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", entryFee=" + entryFee +
                ", rakePercent=" + rakePercent +
                ", level='" + level + '\'' +
                ", maxPlayers=" + maxPlayers +
                ", expiresAt='" + expiresAt + '\'' +
                ", status=" + status +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", isLocked=" + isLocked +
                '}';
    }
}