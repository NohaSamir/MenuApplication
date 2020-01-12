package app.dkh.interviewapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuResponse {

    @SerializedName("items")
    @Expose
    private List<FoodItem> items = null;

    public MenuResponse(List<FoodItem> items) {
        this.items = items;
    }

    public List<FoodItem> getItems() {
        return items;
    }
}
