package dao.impl;

import dao.iDAO;
import dto.ResellerDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResellerDAOGeneric implements iDAO<ResellerDTO, Integer> {
    private static final Map<Integer, ResellerDTO> map = new HashMap<>();

    @Override
    public List<ResellerDTO> getAll() throws Exception {
        return map.values().stream().toList();
    }

    @Override
    public ResellerDTO getById(Integer id) throws Exception {
        if (!map.containsKey(id)) {
            throw new Exception("Invalid reseller id: '" + id + "'.");
        }
        return map.get(id);
    }

    @Override
    public ResellerDTO add(ResellerDTO e) throws Exception {
        map.keySet()
                .stream()
                .max(Integer::compareTo)
                .ifPresent((id) -> e.setId(id + 1));

        return map.put(e.getId(), e);
    }
}
