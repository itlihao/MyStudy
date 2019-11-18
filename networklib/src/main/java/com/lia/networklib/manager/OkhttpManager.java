package com.lia.networklib.manager;

import com.lia.networklib.interceptor.DomainReplaceInterceptor;
import com.lia.networklib.interceptor.LoggingInterceptor;
import com.lia.networklib.listener.onUrlChangeListener;
import com.lia.networklib.parser.DefaultUrlParser;

import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class OkhttpManager {

    private DomainReplaceInterceptor domainReplaceInterceptor;

    private OkHttpClient.Builder builder = new OkHttpClient.Builder();

    static OkhttpManager getInstance() {
        return RetrofitManagerHolder.INSTANCE;
    }

    private static class RetrofitManagerHolder {
        private static final OkhttpManager INSTANCE = new OkhttpManager();
    }

    private OkhttpManager() {
        domainReplaceInterceptor = new DomainReplaceInterceptor(new DefaultUrlParser());
    }

    /**
     * 传入,配置一些本拦截器需要的参数
     */
    OkHttpClient.Builder with() {
        return builder
                .addInterceptor(domainReplaceInterceptor)
                // 添加Log过滤器
                .addInterceptor(new LoggingInterceptor())
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
    }

    void setGlobleDomain(String globleDomain) {
        domainReplaceInterceptor.setGlobalDomainName(globleDomain);
    }

    //region 设置 修改域名监听器

    /**
     * 注册当 Url 的 BaseUrl 被改变时会被回调的监听器
     *
     * @param listener
     */
    public void registerUrlChangeListener(onUrlChangeListener listener) {
        domainReplaceInterceptor.registerUrlChangeListener(listener);
    }

    /**
     * 注销当 Url 的 BaseUrl 被改变时会被回调的监听器
     *
     * @param listener
     */
    public void unregisterUrlChangeListener(onUrlChangeListener listener) {
        domainReplaceInterceptor.unregisterUrlChangeListener(listener);
    }
    //endregion

    //region 全局域名设置

    /**
     * 存放 Domain 的映射关系
     *
     * @param domainName 域名别称
     * @param domainUrl  域名信息
     */
    public void putDomain(String domainName, String domainUrl) {
        domainReplaceInterceptor.putDomain(domainName, domainUrl);
    }

    /**
     * 取出对应 DomainName 的 Url
     *
     * @param domainName 域名别名
     * @return
     */
    public HttpUrl fetchDomain(String domainName) {
        return domainReplaceInterceptor.fetchDomain(domainName);
    }

    /**
     * 删除域名
     *
     * @param domainName 域名别称
     */
    public void removeDomain(String domainName) {
        domainReplaceInterceptor.removeDomain(domainName);
    }
}
