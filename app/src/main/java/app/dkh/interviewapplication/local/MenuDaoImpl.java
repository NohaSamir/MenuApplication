package app.dkh.interviewapplication.local;

import java.util.List;

import app.dkh.interviewapplication.models.FoodItem;
import app.dkh.interviewapplication.models.MenuResponse;
import app.dkh.interviewapplication.models.StateLiveData;
import io.realm.Realm;
import io.realm.RealmResults;

public class MenuDaoImpl implements MenuDao {

    private Realm mRealm;

    public MenuDaoImpl(Realm realm) {
        this.mRealm = realm;
    }

    /**
     * Save list of food items in realm database
     *
     * @param foodItems food items or menu
     */
    @Override
    public void saveMenu(List<FoodItem> foodItems) {
        mRealm.executeTransaction(realm -> realm.copyToRealmOrUpdate(foodItems));
    }

    /**
     * Get menu list from realm database
     *
     * @return StateLiveData with state cache
     */
    @Override
    public StateLiveData<MenuResponse> getMenu() {

        StateLiveData<MenuResponse> menuStateLiveData = new StateLiveData<>();
        //Fetch menu list from realm
        RealmResults<FoodItem> foodItems = mRealm.where(FoodItem.class).findAll();
        //Post Cache
        menuStateLiveData.postCache(new MenuResponse(foodItems));
        //Add Listener to realm database to update live data after any change
        foodItems.addChangeListener(foodItems1 -> menuStateLiveData.postCache(new MenuResponse(foodItems1)));

        return menuStateLiveData;
    }

    /**
     * return food item by id
     *
     * @param id food item id
     * @return food item
     */
    @Override
    public FoodItem getFoodItem(int id) {
        return mRealm.where(FoodItem.class).equalTo("id", id).findFirst();
    }
}
