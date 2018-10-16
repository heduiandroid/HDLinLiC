package com.linli.consumer.iface;

/**
 * Created by tomoyo on 2016/12/19.
 *
 * 在请求成功响应后，有时数据为中data部分为空，对其进行处理
 *
 */

public interface HandleDataNull {

    void dataNull();
}
