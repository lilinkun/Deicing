package com.communication.deicing.http;


import com.communication.deicing.entity.AccountEntity;
import com.communication.deicing.entity.DeicingControlEntity;
import com.communication.deicing.entity.DeicingDeviceEntity;
import com.communication.deicing.entity.DeicingDeviceListEntity;
import com.communication.deicing.entity.MonitorEntity;
import com.communication.deicing.entity.ResponseData;
import com.communication.deicing.entity.UpdateBean;
import com.communication.deicing.entity.UpdateVersionBean;
import com.communication.deicing.http.convert.JsonConvert;
import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableBody;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * author : liguo
 * date : 2021/2/26 11:33
 * description :
 */
public class DeicingService {

//    public static final String PREFIX = "http://192.168.1.210:8070";  //凯
//    public static final String PREFIX = "http://192.168.1.124:8070";  //敏
    public static final String PREFIX = "http://192.168.1.205:8004";  //文
//    public static final String PREFIX = "http://192.168.40.86:8096";
    public static String EXITVENUEORDER = PREFIX + "/life-service/serviceVenueOrder/modifyOrderStatus";


    public static String UPDATEURL = "https://www.pgyer.com/apiv2/app/check";

//    public static String UPDATEVERSIONURL = "http://192.168.56.1:8080/liguo/liguo.html";
    public static String UPDATEVERSIONURL = PREFIX + "/prod-api/app/version/check";

    /**
     * 设备列表
     */
    public static String DEICINGDEVICELIST = PREFIX + "/deicing/list";
    /**
     *路面与气象监测
     */
    public static String MONITORWEATHERINFO = PREFIX + "/deicing/now/pavement";
    /**
     *设备控制信息
     */
    public static String DEVICECONTROLINFO = PREFIX + "/deicing/controlInfo";
    /**
     *设备控制信息
     */
    public static String DEICINGCONTROL = PREFIX + "/deicing/control";
    /**
     * 获取登录用户信息
     */
    public static String GETACCOUNT = PREFIX + "/account";
    /**
     *修改个人信息
     */
    public static String MODIFYPERSONALINFO = PREFIX + "/persons";
    /**
     *获取邮箱验证码
     */
    public static String VCODEEMAIL = PREFIX + "/code/email";
    /**
     * 绑定邮箱
     */
    public static String BINDEMAIL = PREFIX + "/persons/email";
    /**
     *获取路段编号
     */
    public static String HIGHWAYSSN = PREFIX + "/deicing/get/highWays_sn";
    /**
     *获取路段名
     */
    public static String HIGHWAYS = PREFIX + "/deicing/get/highWays";
    /**
     *获取桩号
     */
    public static String HIGHWAYSLENGTH = PREFIX + "/deicing/get/highWaysLength";
    /**
     *获取手机验证码
     */
    public static String VCODEPHONE = PREFIX + "/code/phone";
    /**
     *修改绑定手机
     * (默认删除手机号其它账户绑定信息)
     */
    public static String BINDPHONE = PREFIX + "/persons/phone/change";
    /**
     *修改密码
     */
    public static String CHANGEPASSWORD = PREFIX + "/account/pwd";


    /**
     * 更新apk
     */
    public static Observable<ResponseData<UpdateBean>> updateApk(){

        return OkGo.<ResponseData<UpdateBean>>get(UPDATEURL)
                .params("_api_key", "ea6f638543a55aaef96b9abb39ab1c2e")
                .params("appKey", "d2db71f43bb6e9424a3cf3ac81a50dc6")
                .converter(new JsonConvert<ResponseData<UpdateBean>>() {
                })
                .adapt(new ObservableBody<ResponseData<UpdateBean>>());
    }

    /**
     * 更新apk
     */
    public static Observable<ResponseData<UpdateVersionBean>> updateVersionApk(){

        return OkGo.<ResponseData<UpdateVersionBean>>post(UPDATEVERSIONURL)
                .params("appName", "yh_android")
                .converter(new JsonConvert<ResponseData<UpdateVersionBean>>() {
                })
                .adapt(new ObservableBody<ResponseData<UpdateVersionBean>>());
    }

    /**
     * 获取设备列表
     */
    public static Observable<ResponseData<DeicingDeviceListEntity<List<DeicingDeviceEntity>>>> deicingDeviceList(int pageIndex, int pageSize,
                                                                                                                 String highwaysSn,String highWays,String highwaysLength){

        return OkGo.<ResponseData<DeicingDeviceListEntity<List<DeicingDeviceEntity>>>>get(DEICINGDEVICELIST)
                .params("pageSize",pageSize)
                .params("pageIndex",pageIndex)
                .params("highWays_sn",highwaysSn)
                .params("highWays",highWays)
                .params("highWaysLength",highwaysLength)
                .converter(new JsonConvert<ResponseData<DeicingDeviceListEntity<List<DeicingDeviceEntity>>>>(){
                })
                .adapt(new ObservableBody<ResponseData<DeicingDeviceListEntity<List<DeicingDeviceEntity>>>>());

    }

    /**
     * 获取道路气象信息
     */
    public static Observable<ResponseData<MonitorEntity>> monitorWeatherInfo(String sn){

        return OkGo.<ResponseData<MonitorEntity>>get(MONITORWEATHERINFO)
                .params("sn",sn)
                .converter(new JsonConvert<ResponseData<MonitorEntity>>(){
                })
                .adapt(new ObservableBody<ResponseData<MonitorEntity>>());
    }

    /**
     * 获取设备控制信息
     */
    public static Observable<ResponseData<List<DeicingControlEntity>>> deviceControlInfo(String sn){

        return OkGo.<ResponseData<List<DeicingControlEntity>>>get(DEVICECONTROLINFO)
                .params("sn",sn)
                .converter(new JsonConvert<ResponseData<List<DeicingControlEntity>>>(){
                })
                .adapt(new ObservableBody<ResponseData<List<DeicingControlEntity>>>());
    }

    /**
     * 获取设备控制信息
     */
    public static Observable<ResponseData<String>> deicingcontrol(JSONObject jsonObject){

        return OkGo.<ResponseData<String>>put(DEICINGCONTROL)
                .upJson(jsonObject)
                .converter(new JsonConvert<ResponseData<String>>(){
                })
                .adapt(new ObservableBody<ResponseData<String>>());
    }


    /**
     * 获取登录用户信息
     */
    public static Observable<ResponseData<AccountEntity>> getAccount(){

        return OkGo.<ResponseData<AccountEntity>>get(GETACCOUNT)
                .converter(new JsonConvert<ResponseData<AccountEntity>>(){
                })
                .adapt(new ObservableBody<ResponseData<AccountEntity>>());
    }

    /**
     * 修改个人信息
     */
    public static Observable<ResponseData<Object>> modifyPersonInfo(JSONObject jsonObject){

        return OkGo.<ResponseData<Object>>put(MODIFYPERSONALINFO)
                .upJson(jsonObject)
                .converter(new JsonConvert<ResponseData<Object>>(){
                })
                .adapt(new ObservableBody<ResponseData<Object>>());

    }

    /**
     * 获取邮箱验证码
     * sign：邮箱验证码签名
     * email：邮箱
     */
    public static Observable<ResponseData<Object>> sendVCodePhone(String sign,String phone){

        return OkGo.<ResponseData<Object>>get(VCODEPHONE)
                .params("sign",sign)
                .params("phone",phone)
                .converter(new JsonConvert<ResponseData<Object>>(){
                })
                .adapt(new ObservableBody<ResponseData<Object>>());

    }

    /**
     * 获取邮箱验证码
     * sign：邮箱验证码签名
     * email：邮箱
     */
    public static Observable<ResponseData<Object>> sendVCodeEmail(String sign,String email){

        return OkGo.<ResponseData<Object>>get(VCODEEMAIL)
                .params("sign",sign)
                .params("email",email)
                .converter(new JsonConvert<ResponseData<Object>>(){
                })
                .adapt(new ObservableBody<ResponseData<Object>>());

    }

    /**
     * 绑定邮箱
     * sign：邮箱验证码签名
     * email：邮箱
     */
    public static Observable<ResponseData<Object>> bindEmail(JSONObject jsonObject){

        return OkGo.<ResponseData<Object>>put(BINDEMAIL)
                .upJson(jsonObject)
                .converter(new JsonConvert<ResponseData<Object>>(){
                })
                .adapt(new ObservableBody<ResponseData<Object>>());

    }

    /**
     * 获取路段编号
     */
    public static Observable<ResponseData<ArrayList<String>>> highwaysSn(){

        return OkGo.<ResponseData<ArrayList<String>>>get(HIGHWAYSSN)
                .converter(new JsonConvert<ResponseData<ArrayList<String>>>(){
                })
                .adapt(new ObservableBody<ResponseData<ArrayList<String>>>());

    }

    /**
     * 获取路段名
     */
    public static Observable<ResponseData<ArrayList<String>>> highways(String highWays_sn){

        return OkGo.<ResponseData<ArrayList<String>>>get(HIGHWAYS)
                .params("highWays_sn",highWays_sn)
                .converter(new JsonConvert<ResponseData<ArrayList<String>>>(){
                })
                .adapt(new ObservableBody<ResponseData<ArrayList<String>>>());

    }

    /**
     * 获取桩号
     */
    public static Observable<ResponseData<ArrayList<String>>> highWaysLength(String highWays_sn,String highWays){

        return OkGo.<ResponseData<ArrayList<String>>>get(HIGHWAYSLENGTH)
                .params("highWays_sn",highWays_sn)
                .params("highWays",highWays)
                .converter(new JsonConvert<ResponseData<ArrayList<String>>>(){
                })
                .adapt(new ObservableBody<ResponseData<ArrayList<String>>>());

    }

    /**
     * 修改绑定手机
     * (默认删除手机号其它账户绑定信息)
     */
    public static Observable<ResponseData<Object>> bindPhone(JSONObject jsonObject){

        return OkGo.<ResponseData<Object>>put(BINDPHONE)
                .upJson(jsonObject)
                .converter(new JsonConvert<ResponseData<Object>>(){
                })
                .adapt(new ObservableBody<ResponseData<Object>>());

    }

    /**
     * 修改密码
     */
    public static Observable<ResponseData<Object>> changePassword(String pwd,String newPwd){

        return OkGo.<ResponseData<Object>>put(CHANGEPASSWORD)
                .params("pwd",pwd)
                .params("newPwd",newPwd)
                .converter(new JsonConvert<ResponseData<Object>>(){
                })
                .adapt(new ObservableBody<ResponseData<Object>>());

    }

}
