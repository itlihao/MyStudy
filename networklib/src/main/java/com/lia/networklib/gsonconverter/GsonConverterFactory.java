package com.lia.networklib.gsonconverter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.lia.networklib.ann.Code;
import com.lia.networklib.ann.Content;
import com.lia.networklib.ann.Encrypt;
import com.lia.networklib.ann.Status;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class GsonConverterFactory extends Converter.Factory {

    private final Gson gson;

    private GsonConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    public static GsonConverterFactory create() {
        return create(new Gson());
    }

    public static GsonConverterFactory create(Gson gson) {
        return new GsonConverterFactory(gson);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        String content = "message";
        String code = "errorCode";
        String status = "200"; // 错误码，根据返回数据配置
        for (Annotation annotation : annotations) {
            if (annotation instanceof Content) {
                content = ((Content) annotation).value();
            }
            if (annotation instanceof Code) {
                code = ((Code) annotation).value();
            }
            if (annotation instanceof Status) {
                status = ((Status) annotation).value();
            }
        }
        return new GsonResponseBodyConverter<>(gson, adapter, content, code, status);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonRequestBodyConverter<>(gson, adapter);
    }

    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        boolean content = false;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Encrypt) {
                content = ((Encrypt) annotation).value();
            }
        }
        if (content) {
            return new GsonStringBodyConverter<>();
        } else {
            return super.stringConverter(type, annotations, retrofit);
        }
    }
}
