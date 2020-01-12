package app.dkh.interviewapplication.models;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StateLiveData<T> extends MutableLiveData<StateData<T>> {

    /**
     * Use this to put the Data on a LOADING Status
     */
    public void postLoading() {
        postValue(new StateData<T>().loading());
    }

    /**
     * Use this to put the Data on a ERROR DataStatus
     *
     * @param throwable the error to be handled
     */
    public void postFailure(Throwable throwable) {
        postValue(new StateData<T>().failure(throwable));
    }

    /**
     * Use this to put the Data on a ERROR DataStatus
     *
     * @param error the error to be handled
     */
    public void postError(Error error) {
        postValue(new StateData<T>().error(error));
    }

    /**
     * Use this to put the Data on a SUCCESS DataStatus
     *
     * @param data
     */
    public void postSuccess(T data) {
        postValue(new StateData<T>().success(data));
    }

    public void postCache(T data) {
        postValue(new StateData<T>().cached(data));
    }


    public void request(Call<T> call, Method method) {

        //postLoading();

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {

                if (response.isSuccessful() && response.body() != null) {
                    try {
                        method.invoke(this ,response.body());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                } else {
                    postError(Error.getError());
                }
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                //Case Time out oR no internet display connection error
                if (t instanceof SocketTimeoutException || t instanceof UnknownHostException) {
                    postError(Error.getConnectionError());
                } else
                    postFailure(t);
            }
        });
    }


}
