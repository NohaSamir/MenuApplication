package app.dkh.interviewapplication.remote;

import app.dkh.interviewapplication.models.MenuResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WebService {

    @GET("bins/kvdzh")
    Call<MenuResponse> getMenu();
}
