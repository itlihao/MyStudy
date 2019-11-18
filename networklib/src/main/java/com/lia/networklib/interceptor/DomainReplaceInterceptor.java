package com.lia.networklib.interceptor;


import com.lia.networklib.listener.onUrlChangeListener;
import com.lia.networklib.parser.CheckUtils;
import com.lia.networklib.parser.UrlParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by android on 2018/1/30.
 */

public class DomainReplaceInterceptor implements Interceptor {

    public static final String DOMAIN_NAME = "Domain-Name";

    private final List<onUrlChangeListener> mListeners = new ArrayList<>();
    private final Map<String, HttpUrl> mDomainNameHub = new HashMap<>();

    private UrlParser mUrlParser;

    /*默认域名*/
    private String GLOBAL_DOMAIN_NAME = "me.jessyan.retrofiturlmanager.globalDomainName";

    public DomainReplaceInterceptor(UrlParser mUrlParser) {
        this.mUrlParser = mUrlParser;
    }

    public void putDomain(String domainName, String domainUrl) {
        mDomainNameHub.put(domainName, CheckUtils.checkUrl(domainUrl));
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(processRequest(chain.request()));
    }

    /**
     * 对 {@link Request} 进行一些必要的加工
     * d动态修改域名 并执行 拦截器
     *
     * @param request
     * @return
     */
    public Request processRequest(Request request) {

        Request.Builder newBuilder = request.newBuilder();

        String domainName = obtainDomainNameFromHeaders(request);

        HttpUrl httpUrl;

        Object[] listeners = listenersToArray();

        // 如果有 header,获取 header 中 domainName 所映射的 url,若没有,则检查全局的 BaseUrl,未找到则为null
        if (!isEmpty(domainName)) {
            notifyListener(request, domainName, listeners);
            httpUrl = fetchDomain(domainName);
            newBuilder.removeHeader(DOMAIN_NAME);
        } else {
            notifyListener(request, request.url().toString(), listeners);
            httpUrl = fetchDomain(request.url().toString());
        }

        if (null != httpUrl) {
            HttpUrl newUrl = mUrlParser.parseUrl(httpUrl, request.url(), this.GLOBAL_DOMAIN_NAME);

            if (listeners != null) {
                for (int i = 0; i < listeners.length; i++) {
                    // 通知监听器此 Url 的 BaseUrl 已被改变
                    ((onUrlChangeListener) listeners[i]).onUrlChanged(newUrl, request.url());
                }
            }
            return newBuilder
                    .url(newUrl)
                    .build();
        }

        return newBuilder.build();

    }

    /**
     * 从 @link Request#header(String) 中取出 DomainName 域名
     *
     * @param request
     * @return
     */
    private String obtainDomainNameFromHeaders(Request request) {
        List<String> headers = request.headers(DOMAIN_NAME);
        if (headers == null || headers.size() == 0)
            return null;
        if (headers.size() > 1)
            throw new IllegalArgumentException("Only one Domain-Name in the headers");
        return request.header(DOMAIN_NAME);
    }

    private Object[] listenersToArray() {
        Object[] listeners = null;
        synchronized (mListeners) {
            if (mListeners.size() > 0) {
                listeners = mListeners.toArray();
            }
        }
        return listeners;
    }

    /**
     * 执行 监听器 之前执行的
     *
     * @param request
     * @param domainName
     * @param listeners
     */
    private void notifyListener(Request request, String domainName, Object[] listeners) {
        if (listeners != null) {
            for (int i = 0; i < listeners.length; i++) {
                ((onUrlChangeListener) listeners[i]).onUrlChangeBefore(request.url(), domainName);
            }
        }
    }

    /**
     * 取出对应 DomainName 的 Url
     *
     * @param domainName
     * @return
     */
    public HttpUrl fetchDomain(String domainName) {
        return mDomainNameHub.get(domainName);
    }

    /**
     * 注册当 Url 的 BaseUrl 被改变时会被回调的监听器
     *
     * @param listener
     */
    public void registerUrlChangeListener(onUrlChangeListener listener) {
        synchronized (mListeners) {
            mListeners.add(listener);
        }
    }

    /**
     * 注销当 Url 的 BaseUrl 被改变时会被回调的监听器
     *
     * @param listener
     */
    public void unregisterUrlChangeListener(onUrlChangeListener listener) {
        synchronized (mListeners) {
            mListeners.remove(listener);
        }
    }

    public void removeDomain(String domainName) {
        synchronized (mDomainNameHub) {
            mDomainNameHub.remove(domainName);
        }
    }

    /**
     * Returns true if the string is null or 0-length.
     *
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public void setGlobalDomainName(String globalDomainName) {
        this.GLOBAL_DOMAIN_NAME = globalDomainName;
    }
}
