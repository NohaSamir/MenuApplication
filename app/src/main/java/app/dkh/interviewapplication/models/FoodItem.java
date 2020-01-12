package app.dkh.interviewapplication.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.VisibleForTesting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class FoodItem extends RealmObject implements Parcelable {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("photoUrl")
    @Expose
    private String photoUrl;
    @SerializedName("description")
    @Expose
    private String description;

    public FoodItem() {
    }

    @VisibleForTesting
    public FoodItem(int id, String name, String photoUrl, String description) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.description = description;
    }

    protected FoodItem(Parcel in) {
        id = in.readInt();
        name = in.readString();
        photoUrl = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(photoUrl);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getDescription() {
        return description;
    }
}
