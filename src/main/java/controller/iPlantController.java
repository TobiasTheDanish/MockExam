package controller;

import io.javalin.http.Handler;

public interface iPlantController {
    public Handler getAllPlants();
    public Handler getPlantById();
    public Handler getPlantsByType();
    public Handler addNewPlant();
}
