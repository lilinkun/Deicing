package com.communication.deicing.callback;

import com.communication.deicing.entity.UpdateBean;
import com.communication.deicing.entity.VerificationCodeEntity;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import okhttp3.ResponseBody;

import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;

public class JsonCallback<T> extends AbsCallback {
    private Class<T> clazz;
    private RequestCallback requestCallback;

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    public JsonCallback(Class<T> clazz, RequestCallback requestCallback) {
        this.clazz = clazz;
        this.requestCallback = requestCallback;
    }

    @Override
    public void onStart(Request request) {
        super.onStart(request);

        if (OkGo.getInstance().getOkHttpClient().dispatcher().runningCallsCount() == 0 && requestCallback != null) {
            requestCallback.loading(true);
        }
    }

    @Override
    public Object convertResponse(okhttp3.Response response) throws Throwable {

        if (response != null) {
            ResponseBody responseBody = response.body();

            if (responseBody == null) {
                return null;
            }
            JsonReader jsonReader = new JsonReader(responseBody.charStream());
            Gson gson = new Gson();
            T t = gson.fromJson(jsonReader, clazz);
            UpdateBean base = (UpdateBean) t;

            if (base != null) {
                if (base.code == 0) {
                    return t;
                } else{
                    if (requestCallback != null) {
                        requestCallback.loading(false);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                requestCallback.onFail(base.message);
                            }
                        });

                    }
                }
            }
        }
        return null;
    }

    @Override
    public void onSuccess(Response response) {
        if (requestCallback != null) {
            requestCallback.onSuccess(response);
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
//        if (OkGo.getInstance().getOkHttpClient().dispatcher().runningCallsCount() == 0 && requestCallback != null) {
            requestCallback.loading(false);
//        }
    }

    @Override
    public void onError(Response response) {
        super.onError(response);
        if (requestCallback != null) {
            requestCallback.loading(false);
        }
    }
}
