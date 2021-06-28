package com.communication.deicing.presenter;


import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.entity.UpdateBean;
import com.communication.deicing.entity.UpdateVersionBean;
import com.communication.deicing.http.DeicingService;
import com.communication.deicing.http.rxjavahelper.RxObserver;
import com.communication.deicing.http.rxjavahelper.RxResultHelper;
import com.communication.deicing.http.rxjavahelper.RxSchedulersHelper;
import com.communication.deicing.view.MainView;

/**
 * Created by LG
 * on 2021/6/11
 * Description：
 */
public class MainPresenter extends BasePresenter<MainView> {

    /**
     * 检查更新
     */
    public void checkVersionUpdate(){
        DeicingService.updateVersionApk()
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<UpdateVersionBean>() {
                    @Override
                    public void _onNext(UpdateVersionBean updateVersionBean) {
                        getView().getUpdateVersionDataSuccess(updateVersionBean);
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().getDataFail(errorMessage);
                    }

                });
    }

    /**
     * 检查蒲公英更新
     */
    public void checkUpdate(){
        DeicingService.updateApk()
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<UpdateBean>() {
                    @Override
                    public void _onNext(UpdateBean updateBean) {
                        getView().getUpdateDataSuccess(updateBean);
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().getDataFail(errorMessage);
                    }

                });
    }
    /*public void checkUpdate(){
        UpdateAppUtils.getInstance()
                .apkUrl("")
                .updateTitle("版本更新啦！")
                .updateContent("发现新版本，立即更新")
                .update();
    }*/

}
