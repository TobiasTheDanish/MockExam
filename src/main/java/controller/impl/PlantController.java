package controller.impl;

import controller.iPlantController;
import dao.impl.PlantDAOMock;
import dto.PlantDTO;
import exception.ApiException;
import io.javalin.http.Handler;

public class PlantController implements iPlantController {
    PlantDAOMock dao = new PlantDAOMock();

    @Override
    public Handler getAllPlants() {
        return ctx -> {
            ctx.json(dao.getAllPlants());
        };
    }

    @Override
    public Handler getPlantById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));

            try {
                ctx.json(dao.getPlantById(id));
            } catch (Exception e) {
                throw new ApiException(400, e.getMessage());
            }
        };
    }

    @Override
    public Handler getPlantsByType() {
        return ctx -> {
            String type = ctx.pathParam("type");

            try {
                ctx.json(dao.getPlantsByType(type));
            } catch (Exception e) {
                throw new ApiException(400, e.getMessage());
            }
        };
    }

    @Override
    public Handler addNewPlant() {
        return ctx -> {
            PlantDTO newPlant = ctx.bodyAsClass(PlantDTO.class);

            ctx.json(dao.addPlant(newPlant));
        };
    }
}
