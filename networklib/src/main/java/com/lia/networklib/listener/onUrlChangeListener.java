/**
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lia.networklib.listener;

import okhttp3.HttpUrl;

public interface onUrlChangeListener {

    /**
     * 此方法在框架使用 {@code domainName} 作为 key,从
     * 中取出对应的 BaseUrl 构建新的 Url 之前会被调用
     * <p>
     * 可以使用此回调确保  中是否已经存在自己期望的 BaseUrl
     * 如果不存在可以在此方法中进行 {@code put}
     *
     * @param oldUrl
     * @param domainName
     */
    void onUrlChangeBefore(HttpUrl oldUrl, String domainName);

    /**
     * 当 Url 的 BaseUrl 被改变时回调
     * 调用时间是在接口请求服务器之前
     *
     * @param newUrl
     * @param oldUrl
     */
    void onUrlChanged(HttpUrl newUrl, HttpUrl oldUrl);
}
