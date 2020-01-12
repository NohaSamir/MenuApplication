package app.dkh.interviewapplication.viewmodels;

import android.arch.lifecycle.ViewModel;

import io.realm.Realm;

/**
 * Created by danie on 27-02-2018.
 * Use this as base class if the ViewModel needs to use a Realm instance.
 * This base class will automatically assign and close Realm instances.
 * Remember to call super(); in the constructor of the implementing class
 */


public abstract class RealmViewModel extends ViewModel {
    private Realm _realm;

    RealmViewModel() {
        _realm = Realm.getDefaultInstance();
    }

    /**
     * Get a Realm instance that is tied to the lifecycle of this view model
     *
     * @return a managed Realm instance
     */
    protected Realm getRealm() {
        return _realm;
    }

    @Override
    protected void onCleared() {
        _realm.close();
        super.onCleared();
    }
}