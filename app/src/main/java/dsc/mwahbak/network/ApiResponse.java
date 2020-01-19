package dsc.mwahbak.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import dsc.mwahbak.models.TalentTypeModel;
import dsc.mwahbak.models.User;

public class ApiResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;


    @SerializedName("data")
    @Expose
    private Data data;

    @SerializedName("categories")
    private ArrayList<TalentTypeModel> catModels;

    @SerializedName("user")
    @Expose
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<TalentTypeModel> getCatModels() {
        return catModels;
    }

    public void setCatModels(ArrayList<TalentTypeModel> catModels) {
        this.catModels = catModels;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}