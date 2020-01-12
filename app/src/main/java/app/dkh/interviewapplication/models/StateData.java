package app.dkh.interviewapplication.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class StateData<T> {

    @NonNull
    private DataStatus status;

    @Nullable
    private Error error;

    @Nullable
    private T data;

    @Nullable
    private Throwable throwable;

    public StateData() {
        this.status = DataStatus.CREATED;
        this.data = null;
        this.error = null;
    }

    public StateData<T> loading() {
        this.status = DataStatus.LOADING;
        this.data = null;
        this.error = null;
        return this;
    }

    public StateData<T> success(@NonNull T data) {
        this.status = DataStatus.SUCCESS;
        this.data = data;
        this.error = null;
        return this;
    }

    public StateData<T> cached(@NonNull T data) {
        this.status = DataStatus.CACHED;
        this.data = data;
        return this;
    }

    public StateData<T> error(@NonNull Error error) {
        this.status = DataStatus.ERROR;
        this.data = null;
        this.error = error;
        return this;
    }

    public StateData<T> failure(@NonNull Throwable throwable) {
        this.status = DataStatus.Failure;
        this.data = null;
        this.throwable = throwable;
        return this;
    }


    @NonNull
    public DataStatus getStatus() {
        return status;
    }

    @Nullable
    public T getData() {
        return data;
    }

    @Nullable
    public Throwable getFailure() {
        return throwable;
    }

    @Nullable
    public Error getError() {
        return error;
    }

    public enum DataStatus {
        CREATED,
        SUCCESS,
        ERROR,
        Failure,
        LOADING,
        CACHED
    }
}
