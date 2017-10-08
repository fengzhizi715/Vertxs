package com.cv4j.vertx.utils;

import io.vertx.core.Future;

import java.util.concurrent.CompletableFuture;

/**
 * Created by tony on 2017/10/8.
 */
public class Futures {

    /**
     * 将vertx的Future转换成Java 8的CompletableFuture
     * @param future
     * @param <T>
     * @return
     */
    public static <T> CompletableFuture<T> toCompletableFuture(Future<T> future) {
        CompletableFuture<T> completableFuture = new CompletableFuture<>();
        future.setHandler(ar -> {
            if (ar.succeeded()) {
                completableFuture.complete(ar.result());
            } else {
                completableFuture.completeExceptionally(ar.cause());
            }
        });
        return completableFuture;
    }

    /**
     * 将CompletableFuture转成vertx的Future
     * @param completableFuture
     * @param <T>
     * @return
     */
    public static <T> Future<T> toFuture(CompletableFuture<T> completableFuture) {
        Future<T> future = Future.future();
        completableFuture.thenAccept(r -> future.complete(r))
                .exceptionally(t -> {
                    future.fail(t);
                    return null;
                });
        return future;
    }
}
