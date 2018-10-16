package com.linli.consumer.common;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Converter;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * Created by tomoyo on 2016/12/24.
 */

public class FastJsonResponseConverter<T> implements Converter<ResponseBody, T> {

    public FastJsonResponseConverter(Type type) {
        this.type = type;
    }

    private Type type;

    @Override
    public T convert(ResponseBody value) throws IOException {
        BufferedSource bufferedSource = Okio.buffer(value.source());
        String tempStr = bufferedSource.readUtf8();
        bufferedSource.close();
        return JSON.parseObject(tempStr, type);
    }
}
