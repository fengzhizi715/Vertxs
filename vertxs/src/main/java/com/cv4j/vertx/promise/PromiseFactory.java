package com.cv4j.vertx.promise;

import io.vertx.core.Vertx;

/**
 * Created by tony on 2017/10/18.
 */
public class PromiseFactory {

    private final Vertx vertx;

    /**
     * Initialize a promise factory with a reference to your vertx event loop
     * @param vertx the vertx event loop to run your promises on
     */
    public PromiseFactory(Vertx vertx) {
        this.vertx = vertx;
    }

    /**
     * Create an empty promise.
     *
     * @return a new empty promise
     */
    public Promise create() {
        return Promise.newInstance(vertx);
    }

    /**
     * Create a promise with a list of actions to be executed serially.
     *
     * @param actions the actions to execute
     * @return the promise representing the actions
     */
    public Promise createSerial(PromiseAction ... actions) {
        return Promise.newInstance(vertx).allInOrder(actions);
    }

    /**
     * Create a promise with a list of actions to be executed in parallel.
     *
     * @param actions the actions to execute
     * @return the promise representing the actions
     */
    public Promise createParallel(PromiseAction ... actions) {
        return Promise.newInstance(vertx).all(actions);
    }
}
