package com.entwik.carromcash.models.profile;

import com.entwik.carromcash.models.common.UserDataModel;
import com.entwik.carromcash.models.common.UserWalletDataModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileResponseDataModel {

        @SerializedName("userData")
        @Expose
        private UserDataModel userData;
        @SerializedName("walletData")
        @Expose
        private UserWalletDataModel walletData;
        @SerializedName("leagues")
        @Expose
        private List<LeagueModel> leagues = null;

        public UserDataModel getUserData() {
            return userData;
        }

        public void setUserData(UserDataModel userData) {
            this.userData = userData;
        }

        public UserWalletDataModel getWalletData() {
            return walletData;
        }

        public void setWalletData(UserWalletDataModel walletData) {
            this.walletData = walletData;
        }

        public List<LeagueModel> getLeagues() {
            return leagues;
        }

        public void setLeagues(List<LeagueModel> leagues) {
            this.leagues = leagues;
        }
}
