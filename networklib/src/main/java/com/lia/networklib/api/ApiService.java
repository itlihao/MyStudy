package com.lia.networklib.api;

import com.lia.networklib.base.BaseResult;
import com.lia.networklib.base.UserInterface;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface ApiService {

    /**
     * 测试接口
     */
    @POST("list.htm")
    Observable<BaseResult<UserInterface>> ApiTest(@QueryMap Map<String, String> details);
}
