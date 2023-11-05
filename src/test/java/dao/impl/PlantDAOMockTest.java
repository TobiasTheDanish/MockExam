package dao.impl;

import dto.PlantDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlantDAOMockTest {
    PlantDAOMock dao = new PlantDAOMock();

    @BeforeEach
    void setUp() {
        dao.addPlant(new PlantDTO(1, "Tree", "Oak", 30, 50.0));
        dao.addPlant(new PlantDTO(2, "Flower", "Rose", 2, 10.0));
        dao.addPlant(new PlantDTO(3, "Flower", "Tulip", 100, 5.0));
        dao.addPlant(new PlantDTO(4, "Tree", "Pine", 50, 70.0));
        dao.addPlant(new PlantDTO(5, "Shrub", "Boxwood", 6, 25.0));
        dao.addPlant(new PlantDTO(6, "Flower", "Sunflower", 8, 15.0));
        dao.addPlant(new PlantDTO(7, "Tree", "Willow", 35, 55.0));
        dao.addPlant(new PlantDTO(8, "Shrub", "Juniper", 4, 20.0));
        dao.addPlant(new PlantDTO(9, "Flower", "Daisy", 2, 8.0));
        dao.addPlant(new PlantDTO(10, "Tree", "Birch", 40, 60.0));
        dao.addPlant(new PlantDTO(11, "Tree", "Sequoia", 100, 200.0));
        dao.addPlant(new PlantDTO(12, "Tree", "Giant Redwood", 250, 180.0));
        dao.addPlant(new PlantDTO(13, "Tree", "Coast Douglas Fir", 150, 150.0));
        dao.addPlant(new PlantDTO(14, "Tree", "Mountain Ash", 120, 130.0));
        dao.addPlant(new PlantDTO(15, "Tree", "White Spruce", 100, 110.0));
    }

    @AfterEach
    void tearDown() {
        dao.clear();
    }

    @Test
    void plantsMaxHeight100() {
        List<PlantDTO> res = dao.getPlantsWithMaxHeigth(100);

        assertNotNull(res);
        res.forEach((p) -> {
            assertEquals(100, p.getMaxHeight());
        });
    }

    @Test
    void mapToStringList() {
        List<String> res = dao.mapPlantNames();

        assertNotNull(res);
        assertTrue(res.contains("Oak"));
        assertTrue(res.contains("Birch"));
        assertTrue(res.contains("Daisy"));
        assertTrue(res.contains("Juniper"));
    }

    @Test
    void sortByName() {
        List<PlantDTO> res = dao.getSortedByName();

        assertNotNull(res);
        assertTrue(res.get(0).getPlantName().equals("Birch"));
        assertTrue(res.get(res.size()-1).getPlantName().equals("Willow"));
    }
}