package com.lia.networklib.gsonconverter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.lia.networklib.exception.ServerException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static okhttp3.internal.Util.UTF_8;

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private final JsonParser jsonParser;
    private final String content;
    private final String code;
    private final String codevalue;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter, String content, String code, String codevalue) {
        this.gson = gson;
        this.adapter = adapter;
        jsonParser = new JsonParser();
        this.content = content;
        this.code = code;
        this.codevalue = codevalue;
    }


    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        JsonElement jsonElement = jsonParser.parse(response);
        JsonElement body = jsonElement.getAsJsonObject().get("body");
        if (body != null) {
            String parseCode = body.getAsJsonObject().get(code).getAsString();
            if (!codevalue.equals(parseCode)) {
                value.close();
                String msg = body.getAsJsonObject().get(content).getAsString();
                throw new ServerException(msg, parseCode);
            } else {
                MediaType contentType = value.contentType();
                Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
                InputStream inputStream = new ByteArrayInputStream(response.getBytes());
                Reader reader = new InputStreamReader(inputStream, charset);
                JsonReader jsonReader = gson.newJsonReader(reader);
                try {
                    return adapter.read(jsonReader);
                } finally {
                    value.close();
                }
            }
        } else {
            String statusCode = jsonElement.getAsJsonObject().get(code).getAsString();
            if (!codevalue.equals(statusCode)) {
                value.close();
                String msg = jsonElement.getAsJsonObject().get(content).getAsString();
                throw new ServerException(msg, statusCode);
            } else {
                MediaType contentType = value.contentType();
                Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
                InputStream inputStream = new ByteArrayInputStream(response.getBytes());
                Reader reader = new InputStreamReader(inputStream, charset);
                JsonReader jsonReader = gson.newJsonReader(reader);
                try {
                    T read = adapter.read(jsonReader);
                    return read;
                } finally {
                    value.close();
                }
            }
        }
    }

}
