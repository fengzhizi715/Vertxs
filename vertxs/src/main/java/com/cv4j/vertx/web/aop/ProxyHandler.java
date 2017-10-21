package com.cv4j.vertx.web.aop;

import io.vertx.core.Handler;

/**
 * Created by tony on 2017/10/21.
 */
public class ProxyHandler<RoutingContext> implements Handler<RoutingContext> {

    private Handler<RoutingContext> handler;

    private AopBeforeHandler beforeHandler;

    private AopAfterHandler afterHandler;

    public ProxyHandler(Handler<RoutingContext> handler) {
        this.handler = handler;
    }

    @Override
    public void handle(RoutingContext event) {

        if (beforeHandler != null) {
            beforeHandler.before(event);
        }

        handler.handle(event);

        if (afterHandler != null) {
            afterHandler.after(event);
        }

    }

    public ProxyHandler<RoutingContext> withAop(AopHandler aopHandler) {
        this.beforeHandler = aopHandler;
        this.afterHandler = aopHandler;
        return this;
    }

    public ProxyHandler<RoutingContext> withAopBefore(AopBeforeHandler beforeHandler) {
        this.beforeHandler = beforeHandler;
        return this;
    }

    public ProxyHandler<RoutingContext> withAopAfter(AopAfterHandler afterHandler) {
        this.afterHandler = afterHandler;
        return this;
    }
}
