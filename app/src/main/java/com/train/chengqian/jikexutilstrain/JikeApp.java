package com.train.chengqian.jikexutilstrain;

import android.app.Application;

import org.xutils.x;

/**
 * Created by cheng.qian on 2016/8/18.
 */
public class JikeApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
