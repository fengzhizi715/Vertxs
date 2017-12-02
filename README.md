# vertxs
对Vert.x框架的封装

# Promise

## 1. 基本使用
```java
        Vertx vertx = Vertx.vertx();

        Promise.newInstance(vertx)
                .then((context, onResult) -> {

                    context.put("result", "some text to share");
                    onResult.accept(true);
                })
                .then((context, onResult) -> onResult.accept(context.containsKey("result")))
                .except((context) -> System.out.println("Failure: " + context.encode()))
                .done((context) -> System.out.println("Success: " + context.encode()))
                .timeout(3000)
                .eval();
```

## 2. 多个回调并行
```java
        Vertx vertx = Vertx.vertx();

        Promise.newInstance(vertx)
                .all((context, onResult) -> {
                            System.out.println("Also 'all' call 1");
                            onResult.accept(true);
                        },
                        (context, onResult) -> {
                            System.out.println("Also 'all' call 2");
                            onResult.accept(true);
                        })
                .done((context) -> System.out.println("Success"))
                .eval();
```

## 3. Promise Factory
```java
        Vertx vertx = Vertx.vertx();

        PromiseFactory factory = new PromiseFactory(vertx);
// Promise 1
        factory.create().then((context, onResult) -> {
            System.out.println("a new promise");
            onResult.accept(true);
        }).eval();

// Promise 2
        factory.createParallel((context, onResult) -> {
            System.out.println("a test");
            onResult.accept(true);
        },(context, onResult) -> {
            System.out.println("a test 2");
            onResult.accept(true);
        }).eval();
```

# AOP
```java
        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);
        
        Route route = router.route(HttpMethod.POST, "/some/path");

        ProxyHandler proxyHandler = new ProxyHandler(new Handler<RoutingContext>() {
            @Override
            public void handle(RoutingContext routingContext) {
                HttpServerResponse response = routingContext.response();
                response.setChunked(true);
                response.putHeader("content-type", "text/plain");

                response.write("this is a test route");
                routingContext.response().end();
            }
        });

        proxyHandler.withAop(new AopHandler() {

            @Override
            public void before(Object... args) {
                System.out.println("aop before handler");
            }

            @Override
            public void after(Object... args) {
                System.out.println("aop after handler");
            }

        });

        route.handler(proxyHandler);
        
        server.requestHandler(router::accept).listen(8080);
```