package com.train.chengqian.jikexutilstrain.fragment;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.train.chengqian.jikexutilstrain.R;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.image_view)
public class ImageFragment extends Fragment {
    private static String LOG_TAG = "Image_View";

    @ViewInject(R.id.image_01)
    ImageView imageView01;
    @ViewInject(R.id.image_02)
    ImageView imageView02;
    @ViewInject(R.id.image_03)
    ImageView imageView03;
    @ViewInject(R.id.image_04)
    ImageView imageView04;
    @ViewInject(R.id.image_05)
    ImageView imageView05;
    @ViewInject(R.id.image_06)
    ImageView imageView06;

    String[] urls = {
            "http://img1.imgtn.bdimg.com/it/u=2411105352,612896845&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=207943590,37797841&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=1294713168,3221264383&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2429843031,583271037&fm=21&gp=0.jpg"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            Log.i(LOG_TAG, "null");
        } else {
            Log.i(LOG_TAG, container.toString());
        }
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setPic();
    }

    private void setPic() {
        ImageOptions options = new ImageOptions.Builder()
//                .setAnimation(null) // 设置动画
//                .setAutoRotate(false)   // 设置是否自动旋转
//                .setCircular(false) // 设置是否显示为圆形
                .setCrop(true)  // 设置大小是否起作用
                .setSize(500,400) // 设置图片显示大小
//                .setIgnoreGif(false)    // 设置是否忽略gif动画
//                .setUseMemCache(true)   // 设置是否使用内存缓存
                .build();   //构建options对象

        x.image().bind(imageView01, urls[0], options);
        x.image().bind(imageView02, urls[1], options);
        x.image().bind(imageView03, urls[2], options, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {

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

        // 第一个参数声明用于显示的ImageView，第二个用于声明将要绑定的资源url
        // 第三个参数设置图片加载的选项，第四个传入CallBack对象，用于绑定时的回调
        x.image().bind(imageView04, urls[3], options, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                imageView05.setImageDrawable(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.i(LOG_TAG, "image canceled");
            }

            @Override
            public void onFinished() {

            }
        });

        //使用loadDrawable可以手动取消图片的加载
        Callback.Cancelable cancelable = x.image().loadDrawable(urls[0], options, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {

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
        //可以调用cancelable.cancel()方法取消图片的加载
        //cancelable.cancel();

        //使用x.image().loadFile()可以自定义图片在缓存时的操作
        x.image().loadFile(urls[2], options, new Callback.CacheCallback<File>() {
            @Override
            public boolean onCache(File result) {
                //可以在此自定义在缓存时的操作
                //return false表示不使用缓存
                return false;
            }

            @Override
            public void onSuccess(File result) {

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




}
