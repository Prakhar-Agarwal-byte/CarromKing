package com.entwik.carromcash.models.wallet;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionResponseDataModel implements Parcelable {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("transactionId")
    @Expose
    private String transactionId;
    @SerializedName("amount")
    @Expose
    private int amount;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;

    protected TransactionResponseDataModel(Parcel in) {
        type = in.readString();
        name = in.readString();
        transactionId = in.readString();
        amount = in.readInt();
        state = in.readString();
        createdAt = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(name);
        dest.writeString(transactionId);
        dest.writeInt(amount);
        dest.writeString(state);
        dest.writeString(createdAt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TransactionResponseDataModel> CREATOR = new Creator<TransactionResponseDataModel>() {
        @Override
        public TransactionResponseDataModel createFromParcel(Parcel in) {
            return new TransactionResponseDataModel(in);
        }

        @Override
        public TransactionResponseDataModel[] newArray(int size) {
            return new TransactionResponseDataModel[size];
        }
    };

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public int getAmount() {
        return amount;
    }

    public String getState() {
        return state;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @NonNull
    @Override
    public String toString() {
        return "TransactionResponseDataModel{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", amount=" + amount +
                ", state='" + state + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

}
