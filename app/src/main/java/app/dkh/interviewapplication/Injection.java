package app.dkh.interviewapplication;

import app.dkh.interviewapplication.local.MenuDao;
import app.dkh.interviewapplication.local.MenuDaoImpl;
import app.dkh.interviewapplication.remote.ApiClient;
import app.dkh.interviewapplication.remote.WebService;
import app.dkh.interviewapplication.repositories.FoodItemRepository;
import io.realm.Realm;

public class Injection {

    public static WebService getWebserviceInstance() {
        return ApiClient.getClient().create(WebService.class);
    }

    public static MenuDao getDatabaseInstance(Realm realm) {
        return new MenuDaoImpl(realm);
    }

    public static FoodItemRepository getRepositoryInstance(Realm realm) {
        return new FoodItemRepository(getWebserviceInstance(), getDatabaseInstance(realm));
    }
}
