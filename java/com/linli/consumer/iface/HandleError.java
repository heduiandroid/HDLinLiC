package com.linli.consumer.iface;

/**
 * Created by tomoyo on 2016/11/4.
 */

public interface HandleError<T> {

    void error(Throwable t);
}
