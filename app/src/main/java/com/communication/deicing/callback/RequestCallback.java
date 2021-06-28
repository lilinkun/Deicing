package com.communication.deicing.callback;

import com.lzy.okgo.model.Response;

public interface RequestCallback {
    void onSuccess(Response response);
    void onFail(String str);
    void loading(boolean b);
}
