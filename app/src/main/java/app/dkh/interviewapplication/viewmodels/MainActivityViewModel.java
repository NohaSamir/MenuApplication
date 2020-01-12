package app.dkh.interviewapplication.viewmodels;

import app.dkh.interviewapplication.Injection;
import app.dkh.interviewapplication.models.MenuResponse;
import app.dkh.interviewapplication.models.StateLiveData;
import app.dkh.interviewapplication.repositories.FoodItemRepository;
import io.realm.Realm;


public class MainActivityViewModel extends RealmViewModel {

    private StateLiveData<MenuResponse> _menuItems;
    private FoodItemRepository _repository;

    public MainActivityViewModel() {
        super();
        // Call getRealm() to get a Realm instance that is managed by the view model
        Realm database = getRealm();
        //Get repository instance
        _repository = Injection.getRepositoryInstance(database);
        //Load menu item from repository
        _menuItems = _repository.getFoodItems();
    }

    /**
     * Get menu items as a {@link StateLiveData} list
     *
     * @return StateLiveData of {@link MenuResponse}
     */
    public StateLiveData<MenuResponse> getMenuItems() {
        return _menuItems;
    }


    public void refresh() {
        _menuItems = _repository.getFoodItems();
    }

}
