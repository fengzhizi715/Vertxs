package com.cv4j.vertx.promise;

import io.vertx.core.json.JsonObject;

import java.util.function.Consumer;

/**
 * Created by tony on 2017/10/18.
 */
public interface PromiseAction {

    /**
     * The action to execute.
     *
     * @param context general purpose object for populating with data that can be used by other actions in a promise or
     *                for communicating the result
     * @param onResult the callback that collects the result of any given PromiseAction necessary for
     *                 continuing or completing the chain of actions in a promise.
     */
    void execute(JsonObject context, Consumer<Boolean> onResult);

}
