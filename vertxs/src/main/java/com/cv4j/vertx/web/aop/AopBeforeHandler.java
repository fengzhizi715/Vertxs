package com.cv4j.vertx.web.aop;

/**
 * Created by tony on 2017/10/21.
 */
public interface AopBeforeHandler {

    /**
     * 执行前处理
     *
     * @param args 参数
     */
    void before(Object... args);
}
