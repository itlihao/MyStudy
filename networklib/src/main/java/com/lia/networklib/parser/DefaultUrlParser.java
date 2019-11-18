
package com.lia.networklib.parser;

import okhttp3.HttpUrl;

/**
 * ================================================
 * 默认解析器
 *
 * ================================================
 */
public class DefaultUrlParser implements UrlParser {
    @Override
    public HttpUrl parseUrl(HttpUrl domainUrl, HttpUrl url, String BaseUrl) {

        // 如果 HttpUrl.parse(url); 解析为 null 说明,url 格式不正确,正确的格式为 "https://github.com:443"
        // http 默认端口 80,https 默认端口 443 ,如果端口号是默认端口号就可以将 ":443" 去掉
        // 只支持 http 和 https
        String s = url.toString().replaceAll(BaseUrl, domainUrl.toString());

        return CheckUtils.checkUrl(s);
    }
}
