package com.communication.deicing.http.rxjavahelper;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * user：lqm
 * desc：自己的Observer，减少实现不必要的回调
 */
public abstract class RxObserver<T>  implements Observer<T>{

    @Override
    public void onSubscribe(Disposable d){
        _onSubscribe(d);
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }
    @Override
    public void onError(Throwable e) {

        if (!e.getMessage().contains("Failed to connect to")) {
            if(e.getMessage().contains("network error! http response code is 404 or 5xx!")){
                _onError("服务器或接口异常");
            }else {
                _onError(e.getMessage());
            }
        }else {
            _onError("连接服务器异常");
        }

    }

    @Override
    public void onComplete() {
        _onComplete();
    }

    public void _onSubscribe(Disposable d) {

    }
    public void _onComplete() {

    }

    //抽象方法，必须实现
    public abstract void _onNext(T t);
    public abstract void _onError(String errorMessage);

}