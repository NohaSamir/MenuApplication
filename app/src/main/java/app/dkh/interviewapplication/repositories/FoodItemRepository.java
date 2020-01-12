package app.dkh.interviewapplication.repositories;

import android.support.annotation.NonNull;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import app.dkh.interviewapplication.local.MenuDao;
import app.dkh.interviewapplication.models.Error;
import app.dkh.interviewapplication.models.FoodItem;
import app.dkh.interviewapplication.models.MenuResponse;
import app.dkh.interviewapplication.models.StateLiveData;
import app.dkh.interviewapplication.remote.WebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodItemRepository {

    private MenuDao _cache;
    private WebService _webService;

    public FoodItemRepository(WebService webservice, MenuDao cache) {
        _cache = cache;
        _webService = webservice;
    }

    public StateLiveData<MenuResponse> getFoodItems() {

        StateLiveData<MenuResponse> liveData = _cache.getMenu();

        _webService.getMenu().enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(@NonNull Call<MenuResponse> call, @NonNull Response<MenuResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    _cache.saveMenu(response.body().getItems());
                } else
                    //Set default Error
                    liveData.postError(Error.getError());
            }

            @Override
            public void onFailure(@NonNull Call<MenuResponse> call, @NonNull Throwable t) {
                //Case Time out oR no internet display connection error
                if (t instanceof SocketTimeoutException || t instanceof UnknownHostException) {
                    liveData.postError(Error.getConnectionError());
                } else
                    liveData.postFailure(t);
            }
        });

        //ToDo Send method to method in java to save list after download
        /*Call<MenuResponse> data = _webService.getMenu();
        liveData.request(data);*/
        return liveData;
    }

    public FoodItem getFoodItem(int id) {
        return _cache.getFoodItem(id);
    }
}
