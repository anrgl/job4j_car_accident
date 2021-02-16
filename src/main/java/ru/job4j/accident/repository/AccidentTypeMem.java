package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentTypeMem {
    private final  Map<Integer, AccidentType> types = new HashMap<>();

    private AccidentTypeMem() {
        types.put(0, AccidentType.of(0, "Две машины"));
        types.put(1, AccidentType.of(1, "Машина и человек"));
        types.put(2, AccidentType.of(2, "Машина и велосипед"));
    }

    public Map<Integer, AccidentType> findAllAccidentTypes() {
        return types;
    }

    public AccidentType findAccidentTypeById(int typeId) {
        return types.get(typeId);
    }
}
