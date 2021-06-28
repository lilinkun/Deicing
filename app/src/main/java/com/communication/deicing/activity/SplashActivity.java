package com.communication.deicing.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.widget.ImageView;

import com.communication.deicing.R;
import com.communication.deicing.base.BaseActivity;
import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.util.MethodsUtil;
import com.gyf.immersionbar.ImmersionBar;


/**
 * 初始app界面
 */
public class SplashActivity extends BaseActivity {
    private static final long DURATION = 3000;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

        ImmersionBar.with(this).transparentBar().init();
        ImageView alphaAnimatorImageView = findViewById(R.id.iv);
        ObjectAnimator alphaObjectAnimator = ObjectAnimator.ofFloat(alphaAnimatorImageView, "alpha", 1f, 0.3f);
        alphaObjectAnimator.setDuration(DURATION);
        alphaObjectAnimator.start();
        alphaObjectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                if (MethodsUtil.logined(SplashActivity.this)) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
