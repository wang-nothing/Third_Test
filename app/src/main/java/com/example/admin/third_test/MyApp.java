package com.example.admin.third_test;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))//设置内存缓存区大小
                .memoryCacheSize(2 * 1024 * 1024)//设置缓存区大小
                .memoryCacheExtraOptions(320,320)//缓存图片最大的宽度与高度 px
                .diskCacheSize(50*1024*1024)//设置sd卡缓存的空间大小
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//sd卡缓存图片的命名 使用md5加密方式

                .diskCacheFileCount(100)//缓存文件的最大数量
                .writeDebugLogs()//写入日志
                .threadPoolSize(3)//线程池
                .build();
//对imageLoader进行初使化

        ImageLoader.getInstance().init(configuration);
    }

}

