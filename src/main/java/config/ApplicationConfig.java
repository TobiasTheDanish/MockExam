package config;

import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import routing.Router;

public class ApplicationConfig {

    public static void configuration(JavalinConfig config) {
        config.http.defaultContentType = "application/json";
        config.routing.contextPath = "/api";
        config.plugins.enableRouteOverview("routes");
    }

    public static void startServer(Javalin app, int port) {
        app.updateConfig(ApplicationConfig::configuration);
        app.routes(Router.getRoutes(app));
        app.start(port);
    }

    public static void stopServer(Javalin app) {
        app.stop();
    }
}
