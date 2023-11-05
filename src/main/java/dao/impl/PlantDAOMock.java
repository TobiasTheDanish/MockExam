package dao.impl;

import dao.iPlantDAO;
import dto.PlantDTO;

import java.util.*;

public class PlantDAOMock implements iPlantDAO {
    private static final Map<Integer, PlantDTO> plantMap = new HashMap<>();

    @Override
    public List<PlantDTO> getAllPlants() {
        return plantMap.values().stream().toList();
    }

    @Override
    public PlantDTO getPlantById(int id) throws Exception {
        if (!plantMap.containsKey(id)) {
            throw new Exception("Invalid plant id: '" + id + "'.");
        }
        return plantMap.get(id);
    }

    @Override
    public List<PlantDTO> getPlantsByType(String type) throws Exception {
         List<PlantDTO> res = plantMap.values()
                .stream()
                .filter((p) -> p.getPlantType().equals(type))
                .toList();

         if (res.isEmpty()) {
             throw new Exception("Invalid plant type. No plants exists with this type '" + type + "'.");
         }

         return res;
    }

    @Override
    public PlantDTO addPlant(PlantDTO plant) {
         plantMap.keySet()
                 .stream()
                 .max(Integer::compareTo)
                 .ifPresent((id) -> plant.setId(id + 1));

         plantMap.put(plant.getId(), plant);

         return plantMap.get(plant.getId());
    }

    public void clear() {
        plantMap.clear();
    }

    public List<PlantDTO> getPlantsWithMaxHeigth(int i) {
        return plantMap.values()
                .stream()
                .filter((p) -> p.getMaxHeight() == i)
                .toList();
    }

    public List<String> mapPlantNames() {
        return plantMap.values()
                .stream()
                .map(PlantDTO::getPlantName)
                .toList();
    }

    public List<PlantDTO> getSortedByName() {
        return plantMap.values()
                .stream()
                .sorted(Comparator.comparing(PlantDTO::getPlantName))
                .toList();
    }
}
