package dao.impl;

import dao.iDAO;
import dto.PlantDTO;
import dto.ResellerDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlantDAOGeneric implements iDAO<PlantDTO, Integer> {
    private static final Map<Integer, PlantDTO> map = new HashMap<>();

    @Override
    public List<PlantDTO> getAll() throws Exception {
        return map.values().stream().toList();
    }

    @Override
    public PlantDTO getById(Integer id) throws Exception {
        if (!map.containsKey(id)) {
            throw new Exception("Invalid reseller id: '" + id + "'.");
        }
        return map.get(id);
    }

    @Override
    public PlantDTO add(PlantDTO e) throws Exception {
        map.keySet()
                .stream()
                .max(Integer::compareTo)
                .ifPresent((id) -> e.setId(id + 1));

        return map.put(e.getId(), e);
    }
}
