package dsc.mwahbak.network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import dsc.mwahbak.models.TalentTypeModel;


public class Data {

    @SerializedName("categories")
    private ArrayList<TalentTypeModel> catModels;
}
