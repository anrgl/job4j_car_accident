package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(4);
    private final Map<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        accidents.put(0, new Accident(
                0,
                "Irina Sorokina",
                "Accident: 2 cars",
                "Moscow, Lenin street"));
        accidents.put(1, new Accident(
                1,
                "Oleg Victorovich",
                "Accident: 1 car with bad road",
                "Ufa, Unknown road"));
        accidents.put(2, new Accident(
                2,
                "Ivan Ivanov",
                "No information",
                "No information"));
        accidents.put(3, new Accident(
                3,
                "NASA",
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
}
