package com.communication.deicing.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LG
 * on 2021/6/23  15:50
 * Description：
 */
public class ActivityUtil {

    public static List<Activity> activityList = new ArrayList<>();

    // 添加Activity
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // 移除所有Activity
    public static void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}
