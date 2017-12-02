# vertxs
对Vert.x框架的封装


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