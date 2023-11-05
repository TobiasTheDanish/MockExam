package dao.impl;

import config.HibernateConfig;
import dto.PlantDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Plant;
import model.Reseller;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlantCenterDAOTest {
    private static EntityManagerFactory emf;
    private static PlantCenterDAO dao;

    @BeforeAll
    static void beforeAll() {
        emf = HibernateConfig.getEntityManagerFactoryConfig("plant_center_test");
        dao = PlantCenterDAO.getInstance(emf);
    }

    @BeforeEach
    void setUp() {

        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(new Plant(new PlantDTO(1, "Tree", "Oak", 30, 50.0)));
            em.persist(new Plant(new PlantDTO(2, "Flower", "Rose", 2, 10.0)));
            em.persist(new Plant(new PlantDTO(3, "Flower", "Tulip", 100, 5.0)));
            em.persist(new Plant(new PlantDTO(4, "Tree", "Pine", 50, 70.0)));
            em.persist(new Plant(new PlantDTO(5, "Shrub", "Boxwood", 6, 25.0)));
            em.persist(new Plant(new PlantDTO(6, "Flower", "Sunflower", 8, 15.0)));
            em.persist(new Plant(new PlantDTO(7, "Tree", "Willow", 35, 55.0)));
            em.persist(new Plant(new PlantDTO(8, "Shrub", "Juniper", 4, 20.0)));
            em.persist(new Plant(new PlantDTO(9, "Flower", "Daisy", 2, 8.0)));
            em.persist(new Plant(new PlantDTO(10, "Tree", "Birch", 40, 60.0)));
            em.persist(new Plant(new PlantDTO(11, "Tree", "Sequoia", 100, 200.0)));
            em.persist(new Plant(new PlantDTO(12, "Tree", "Giant Redwood", 250, 180.0)));
            em.persist(new Plant(new PlantDTO(13, "Tree", "Coast Douglas Fir", 150, 150.0)));
            em.persist(new Plant(new PlantDTO(14, "Tree", "Mountain Ash", 120, 130.0)));
            em.persist(new Plant(new PlantDTO(15, "Tree", "White Spruce", 100, 110.0)));

            Reseller r1 = Reseller.builder()
                    .name("Lyngby Plantecenter")
                    .address("Firskovvej 18")
                    .phone("33212334")
                    .plants(new HashSet<>())
                    .build();

            em.persist(r1);
            em.getTransaction().commit();
        }
    }

    @AfterEach
    void tearDown() {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Plant p").executeUpdate();
            em.createQuery("DELETE FROM Reseller r").executeUpdate();
            //em.createNativeQuery("DELETE FROM reseller_plant").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE plant_id_seq RESTART WITH 1").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE reseller_id_seq RESTART WITH 1").executeUpdate();
            em.getTransaction().commit();
        }
    }

    @Test
    void getAllPlants() {
        var res = dao.getAllPlants();
        assertNotNull(res);
        assertEquals(15, res.size());
    }

    @Test
    void getPlantById() {
        Plant res = dao.getPlantById(14); //14, "Tree", "Mountain Ash", 120, 130.0
        assertNotNull(res);
        assertEquals(14, res.getId());
        assertEquals("Tree", res.getPlantType());
        assertEquals("Mountain Ash", res.getPlantName());
        assertEquals(120, res.getMaxHeight());
        assertEquals(130, res.getPrice());
    }

    @Test
    void getPlantsByType() {
        List<Plant> res = dao.getPlantsByType("Shrub");

        assertNotNull(res);
        assertEquals(2, res.size());
    }

    @Test
    void addPlant() {
        Plant newPlant = dao.addPlant(new PlantDTO(16, "Flower", "Rose", 15, 39.99));
        assertNotNull(newPlant);
        assertEquals(16, newPlant.getId());
        assertEquals("Flower", newPlant.getPlantType());
        assertEquals("Rose", newPlant.getPlantName());
        assertEquals(15, newPlant.getMaxHeight());
        assertEquals(39.99, newPlant.getPrice());
    }

    @Test
    void deletePlant() {
        Plant deletedPlant = dao.deletePlant(15); // 15, "Tree", "White Spruce", 100, 110.0
        assertNotNull(deletedPlant);
        assertEquals(15, deletedPlant.getId());
        assertEquals("Tree", deletedPlant.getPlantType());
        assertEquals("White Spruce", deletedPlant.getPlantName());
        assertEquals(100, deletedPlant.getMaxHeight());
        assertEquals(110.0, deletedPlant.getPrice());
    }

    @Test
    void addPlantToReseller() {
        dao.addPlant(new PlantDTO(16, "Flower", "Rose", 15, 39.99));
        Reseller r = dao.addPlantToReseller(1, 16);

        assertNotNull(r);
        assertEquals(1, r.getPlants().size());
    }

    @Test
    void getPlantsByReseller() {
        dao.addPlantToReseller(1, 2);
        dao.addPlantToReseller(1, 3);
        dao.addPlantToReseller(1, 9);
        dao.addPlantToReseller(1, 13);
        List<Plant> plants = dao.getPlantsByReseller(1);

        assertNotNull(plants);
        assertEquals(4, plants.size());
    }
}