package app.dkh.interviewapplication.local;

import java.util.List;

import app.dkh.interviewapplication.models.FoodItem;
import app.dkh.interviewapplication.models.MenuResponse;
import app.dkh.interviewapplication.models.StateLiveData;

public interface MenuDao {

    void saveMenu(List<FoodItem> foodItems);

    FoodItem getFoodItem(int id);

    StateLiveData<MenuResponse> getMenu();

}
