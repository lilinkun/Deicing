package com.communication.deicing.http.rxjavahelper;


import com.communication.deicing.entity.ResponseData;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * user：lqm
 * desc：Rx处理服务器返回,
 *      服务器的返回的数据格式一般都是一致的，所有我们每个网络请求都可以使
 *      用compose(RxResultHelper.handleResult())来处理服务器返回，一般服务器返回成功码为200，
 *      相应改一下返回码的判断就行了
 */

public class RxResultHelper {

    private static final int RESPONSE_SUCCESS_CODE = 1111; //大部分为1111
    private static final int RESPONSE_ERROR_CODE = -1;



    public static <T> ObservableTransformer<ResponseData<T>, T> handleResult() {
        return new ObservableTransformer<ResponseData<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<ResponseData<T>> tObservable) {
                return tObservable.flatMap(
                        new Function<ResponseData<T>, Observable<T>>() {
                            @Override
                            public Observable<T> apply(ResponseData<T> tResponseData) throws Exception {
                                //可以相应更改
                                if (tResponseData.getCode() == RESPONSE_SUCCESS_CODE ) {
                                    if (tResponseData.getData() != null) {
                                        return Observable.just(tResponseData.getData());
                                    }else {
                                        Object o = 1;
                                        T t = (T)o;
                                        return Observable.just(t);
                                    }
                                } else if (tResponseData.getCode() == RESPONSE_ERROR_CODE) {
                                    return Observable.error(new Exception(tResponseData.getMessage()));
                                } else {
                                    return Observable.empty();
                                }
                            }
                        }
                );
            }

        };
    }

}
