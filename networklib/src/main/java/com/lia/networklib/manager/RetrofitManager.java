package com.lia.networklib.manager;

import com.lia.networklib.api.ApiService;
import com.lia.networklib.gsonconverter.GsonConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitManager {

    private static final String BASEURL = "http://www.sosoapi.com/demo/swagger/user/complex/";

    private OkHttpClient build;

    //region 单例构建
    //私有的构造方法，防止new
    private RetrofitManager() {
    }

    public static RetrofitManager getInstance() {
        return StaticClassSingletonHolder.instance;
    }

    /**
     * 静态内部类
     */
    private static class StaticClassSingletonHolder {
        //第一次加载内部类的时候，实例化单例对象
        private static final RetrofitManager instance = new RetrofitManager();
    }

    private Retrofit mRetrofit;

    private Retrofit getRetrofit() {
        if (mRetrofit == null) {
            String domainName = BASEURL;
            this.build = OkhttpManager.getInstance().with().build();
            OkhttpManager.getInstance().setGlobleDomain(domainName);
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(domainName)
                    //使用rxjava
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    //使用Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(build)
                    .build();
            return mRetrofit;
        } else {
            return mRetrofit;
        }
    }

    public ApiService getNetApi() {
        return getRetrofit().create(ApiService.class);
    }

    public void addDomain(String domainName, String domain) {
        OkhttpManager.getInstance().putDomain(domainName, domain);
    }

    // 配置多URL时设置全局的域名
    public void setDomain(String url) {
        this.build = OkhttpManager.getInstance().with().build();
        OkhttpManager.getInstance().setGlobleDomain(url);
        mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                //使用rxjava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //使用Gson
                .addConverterFactory(GsonConverterFactory.create())
                .client(build)
                .build();
    }
}
