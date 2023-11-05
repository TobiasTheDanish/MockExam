package dao.impl;

import dao.iPlantCenterDAO;
import dto.PlantDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Plant;
import model.Reseller;

import java.util.List;

public class PlantCenterDAO implements iPlantCenterDAO {
    private static PlantCenterDAO instance = null;
    private final EntityManagerFactory emf;

    private PlantCenterDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public static PlantCenterDAO getInstance(EntityManagerFactory emf) {
        if (instance == null) {
            instance = new PlantCenterDAO(emf);
        }

        return instance;
    }
    @Override
    public List<Plant> getAllPlants() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            List<Plant> res = em.createQuery("SELECT p FROM Plant p", Plant.class).getResultList();
            em.getTransaction().commit();

            return res;
        }
    }

    @Override
    public Plant getPlantById(int id) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Plant res = em.find(Plant.class, id);
            em.getTransaction().commit();
            return res;
        }
    }

    @Override
    public List<Plant> getPlantsByType(String type) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            List<Plant> res = em.createQuery("SELECT p FROM Plant p WHERE plantType = :type", Plant.class)
                    .setParameter("type", type)
                    .getResultList();
            em.getTransaction().commit();
            return res;
        }
    }

    @Override
    public Plant addPlant(PlantDTO plant) {
        Plant p = new Plant(plant);
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        }
        return p;
    }

    @Override
    public Plant deletePlant(int id) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Plant p = em.find(Plant.class, id);
            em.remove(p);
            em.getTransaction().commit();

            return p;
        }
    }

    @Override
    public Reseller addPlantToReseller(int resellerId, int plantId) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Reseller r = em.find(Reseller.class, resellerId);
            Plant p = em.find(Plant.class, plantId);
            r.addPlant(p);
            em.getTransaction().commit();

            return r;
        }
    }

    @Override
    public List<Plant> getPlantsByReseller(int resellerId) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            List<Plant> res = em.find(Reseller.class, resellerId).getPlants().stream().toList();
            em.getTransaction().commit();

            return res;
        }
    }
}
