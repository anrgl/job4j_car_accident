package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(0);
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final List<AccidentType> types = new ArrayList<>();

    private AccidentMem() {
        types.add(AccidentType.of(0, "Две машины"));
        types.add(AccidentType.of(1, "Машина и человек"));
        types.add(AccidentType.of(2, "Машина и велосипед"));
        addAccident(new Accident(
                "Irina Sorokina",
                types.get(0),
                "Accident: 2 cars",
                "Moscow, Lenin street"));
        addAccident(new Accident(
                "Oleg Victorovich",
                types.get(1),
                "Accident: 1 car with bad road",
                "Ufa, Unknown road"));
        addAccident(new Accident(
                "Ivan Ivanov",
                types.get(2),
                "No information",
                "No information"));
        addAccident(new Accident(
                "NASA",
                types.get(1),
                "Accident on Mars",
                "Solar system, Mars"));
    }

    public void addAccident(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(ACCIDENT_ID.getAndIncrement());
        }
        accidents.put(accident.getId(), accident);
    }

    public Map<Integer, Accident> getAccidents() {
        return accidents;
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public void updateAccident(int id, Accident accident) {
        accidents.replace(id, accident);
    }

    public List<AccidentType> findAllAccidentTypes() {
        return types;
    }

    public AccidentType findAccidentTypeById(int typeId) {
        return types.get(typeId);
    }
}
