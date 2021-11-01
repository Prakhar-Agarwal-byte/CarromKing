package com.entwik.carromcash.models.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeagueModel {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("isLocked")
    @Expose
    private boolean isLocked;
    @SerializedName("description")
    @Expose
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}