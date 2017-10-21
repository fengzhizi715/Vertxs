package com.cv4j.vertx.web.aop;

/**
 * Created by tony on 2017/10/21.
 */
public interface AopAfterHandler {

    /**
     * 执行后处理
     *
     * @param args 参数
     */
    void after(Object... args);
}
