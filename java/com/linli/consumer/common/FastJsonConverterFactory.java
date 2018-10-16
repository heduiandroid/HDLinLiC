package com.linli.consumer.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by tomoyo on 2016/12/24.
 */

public class FastJsonConverterFactory extends Converter.Factory{

    public static FastJsonConverterFactory create(){
        return new FastJsonConverterFactory();
    }


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new FastJsonResponseConverter<>(type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new FastJsonRequestConverter<>();
    }

    /*@Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new FastJsonResponseConverter<>(type);
    }*/

    public FastJsonConverterFactory() {
        super();
    }

}
