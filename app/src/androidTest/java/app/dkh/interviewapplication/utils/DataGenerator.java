package app.dkh.interviewapplication.utils;

import java.util.ArrayList;

import app.dkh.interviewapplication.models.FoodItem;

public class DataGenerator {


    public static ArrayList<FoodItem> getMenuList(int numOfItems) {
        ArrayList<FoodItem> list = new ArrayList<>();
        for (int i = 0; i < numOfItems; i++) {

            list.add(new FoodItem(i
                    , "French Fries"
                    , "https://fthmb.tqn.com/SE_uop5lysQjgJZHgl4q-zVoRPA=/960x0/filters:no_upscale()/hot-chips-fresh-145047747-584f066c3df78c491e1f1d8a.jpg"
                    , "Custom premium cut by farm frites. Add melted cheese for 7LE - chili con carne for 9LE"));

        }

        return list;
    }
}
