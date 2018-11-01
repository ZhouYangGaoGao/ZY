package com.zhy.zlib.manager;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import com.zhy.zlib.Base.LibActivity;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出 
 *
 */
public class AppManager {
    private static Stack<LibActivity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * 单实例 , UI无需考虑多线程同步问题 
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到栈 
     */
    public void addActivity(LibActivity activity) {
        if (activityStack == null) {
            activityStack = new Stack<LibActivity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（栈顶Activity） 
     */
    public LibActivity currentActivity() {
        if (activityStack == null || activityStack.isEmpty()) {
            return null;
        }
        LibActivity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 获取当前Activity（栈顶Activity） 没有找到则返回null 
     */
    public LibActivity findActivity(Class<?> cls) {
        LibActivity activity = null;
        for (LibActivity aty : activityStack) {
            if (aty.getClass().equals(cls)) {
                activity = aty;
                break;
            }
        }
        return activity;
    }

    /**
     * 结束当前Activity（栈顶Activity） 
     */
    public void finishActivity() {
        LibActivity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity(重载) 
     */
    public void finishActivity(Activity activity) {

        if (activity != null) {
            // 为与系统Activity栈保持一致，且考虑到手机设置项里的"不保留活动"选项引起的Activity生命周期调用onDestroy()方法所带来的问题,此处需要作出如下修正 
            if(activity.isFinishing()){
                activityStack.remove(activity);
                //activity.finish(); 
                activity = null;
            }
        }

    }

    /**
     * 结束指定的Activity(重载) 
     */
    public void finishActivity(Class<?> cls) {
        for (LibActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 关闭除了指定activity以外的全部activity 如果cls不存在于栈中，则栈全部清空 
     *
     * @param cls
     */
    public void finishOthersActivity(Class<?> cls) {
        for (LibActivity activity : activityStack) {
            if (!(activity.getClass().equals(cls))) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity 
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 应用程序退出 
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            System.exit(0);
        }
    }
}

