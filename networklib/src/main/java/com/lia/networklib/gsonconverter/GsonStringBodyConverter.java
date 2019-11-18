package com.lia.networklib.gsonconverter;


import java.nio.charset.Charset;

import okhttp3.MediaType;
import retrofit2.Converter;


/**
 * Created by android on 2017/12/6.
 */
final class GsonStringBodyConverter<T> implements Converter<T, String> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    GsonStringBodyConverter() {
    }

    @Override
    public String convert(T value) {
        return (String) value;
    }
}
