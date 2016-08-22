package com.train.chengqian.jikexutilstrain.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.train.chengqian.jikexutilstrain.R;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.http_view)
public class HttpFragment extends Fragment {
    @ViewInject(R.id.imageView)
    ImageView imageView;

    private static final String LOG_TAG = "XUtils_Http";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    String baidu_url = "http://www.baidu.com";

    @Event(R.id.get)
    private void onGetClick(View view) {
        RequestParams params = new RequestParams(baidu_url);
        Log.i(LOG_TAG,"Http Get Start");
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //Snackbar.make(null, result, Snackbar.LENGTH_SHORT).show();
                Log.i(LOG_TAG,result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("Http Get Error");

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                System.out.println("Finish");
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    String stu_url = "http://xk.urp.seu.edu.cn/jw_service/service/stuCurriculum.action";

    @Event(R.id.post)
    private void onPostClick(View view) {
        RequestParams params = new RequestParams(stu_url);
        params.addBodyParameter("returnStr", "");
        params.addBodyParameter("queryStudentId", "213132251");
        params.addBodyParameter("queryAcademicYear", "15-16-3");
        //log
        Log.i(LOG_TAG,"Http Post Start");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i(LOG_TAG,"Http Post Success"+result.length());
                Log.i(LOG_TAG,result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i(LOG_TAG,"Http Post Error");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.i(LOG_TAG,"Http Post Canceled");
            }

            @Override
            public void onFinished() {
                Log.i(LOG_TAG,"Http Post Finished");
            }
        });
    }

    @Event(R.id.other)
    private void onOtherClick(View view) {
        RequestParams params = new RequestParams(baidu_url);
        params.addParameter("lala", "ee");
        x.http().request(HttpMethod.PUT, params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    String url = "http://www.jikexueyuan.com";

    @Event(R.id.upload)
    private void onUploadClick(View view) {
        String path = "/mnt/sdcard/Download/star.png";
        RequestParams params = new RequestParams(url);
        params.setMultipart(true);
        params.addBodyParameter("file", new File(path));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Event(R.id.download)
    private void onDownloadClick(View view) {
        url = "http://img4.imgtn.bdimg.com/it/u=3419365682,3179513322&fm=21&gp=0.jpg";
        RequestParams params = new RequestParams(url);
        final String fileName = Environment.getExternalStorageDirectory() + "/Download/star_2.png";
        params.setSaveFilePath(fileName);
        params.setAutoRename(false);
        x.http().get(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result) {
                System.out.println("Download Success");
                ImageOptions options = new ImageOptions.Builder()
                        .setCrop(true)
                        .setSize(800, 450)
                        .build();
                x.image().bind(imageView, fileName, options);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(LOG_TAG,ex.toString());
                Log.i(LOG_TAG, "Download Errored");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                Log.i(LOG_TAG, "Download Finished");
            }

            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {
                Log.i(LOG_TAG, "Download Started");
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                // 用于监视下载的进度
            }
        });

    }

    @Event(R.id.cache)
    private void onCacheClick(View view) {
        RequestParams params = new RequestParams(baidu_url);
        // 设置缓存存在的最大周期
        params.setCacheMaxAge(1000*60);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i(LOG_TAG, "onSuccess" + "<<<" + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                Log.i(LOG_TAG, "onCache" + "<<<" + result);
                //true表示使用缓存
                //false表示不适用缓存，始终进行网络访问
                return false;
            }
        });
    }
        
    
}
