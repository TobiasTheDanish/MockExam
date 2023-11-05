package dao;

import dto.PlantDTO;
import model.Plant;
import model.Reseller;

import java.util.List;

public interface iPlantCenterDAO {
    List<Plant> getAllPlants();
    Plant getPlantById(int id);
    List<Plant> getPlantsByType(String type);
    Plant addPlant(PlantDTO plant);
    Plant deletePlant(int id);
    Reseller addPlantToReseller(int resellerId, int plantId);
    List<Plant> getPlantsByReseller(int resellerId);

}
