package routing;

import controller.impl.PlantController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

class PlantRoutes {
    private final static PlantController controller = new PlantController();
    protected static EndpointGroup getRoutes() {
        return () -> {
            get(controller.getAllPlants());
            post(controller.addNewPlant());

            path("{id}", () -> {
                get(controller.getPlantById());
            });
            path("types/{type}", () -> {
                get(controller.getPlantsByType());
            });
        };
    }
}
