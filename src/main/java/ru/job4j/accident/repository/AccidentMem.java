package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(0);
    private final Map<Integer, Accident> accidents = new HashMap<>();

    private AccidentMem() {
        addAccident(new Accident(
                "Irina Sorokina",
                "Accident: 2 cars",
                "Moscow, Lenin street"));
        addAccident(new Accident(
                "Oleg Victorovich",
                "Accident: 1 car with bad road",
                "Ufa, Unknown road"));
        addAccident(new Accident(
                "Ivan Ivanov",
                "No information",
                "No information"));
        addAccident(new Accident(
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
