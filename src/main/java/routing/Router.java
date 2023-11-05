package routing;

import dto.ErrorMessage;
import exception.ApiException;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;

import java.time.LocalDateTime;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

    public static EndpointGroup getRoutes(Javalin app) {
        return () -> {
            path("plants", PlantRoutes.getRoutes());

            app.exception(ApiException.class, (e, ctx) -> {
                ctx.status(e.getStatusCode());
                ctx.json(new ErrorMessage(e.getStatusCode(), e.getMessage(), LocalDateTime.now()));
            });
        };
    }
}
