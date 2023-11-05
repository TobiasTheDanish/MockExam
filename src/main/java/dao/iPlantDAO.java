package dao;

import dto.PlantDTO;

import java.util.List;

public interface iPlantDAO {
    List<PlantDTO> getAllPlants();
    PlantDTO getPlantById(int id) throws Exception;
    List<PlantDTO> getPlantsByType(String type) throws Exception;
    PlantDTO addPlant(PlantDTO plant);

}
