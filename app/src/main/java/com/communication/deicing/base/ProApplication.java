package com.communication.deicing.base;

import android.app.Application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.communication.deicing.http.interceptor.HttpInterceptor;
import com.communication.deicing.util.ConstantsUtil;
import com.communication.deicing.util.MethodsUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;

import okhttp3.OkHttpClient;

public class ProApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initMap();

        initOkgo();
    }

    private void initMap(){

        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        // 默认本地个性化地图初始化方法
        SDKInitializer.initialize(this);

        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

    }


    private void initOkgo() {
        HttpHeaders headers = new HttpHeaders();
        headers.put(ConstantsUtil.AUTHORIZATION, MethodsUtil.getValueByKey(ConstantsUtil.AUTHORIZATION, this));

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpInterceptor loggingInterceptor = new HttpInterceptor("OkGo",this);
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(java.util.logging.Level.INFO);
        builder.addInterceptor(loggingInterceptor);

        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);

        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                .setRetryCount(3)
                .addCommonHeaders(headers);
    }
}
