package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(0);
    private final Map<Integer, Accident> accidents = new HashMap<>();

    private AccidentMem() {

        addAccident(Accident.of(
                "Irina Sorokina",
                AccidentType.of(0, "Две машины"),
                "Accident: 2 cars",
                "Moscow, Lenin street"));
        addAccident(Accident.of(
                "Oleg Victorovich",
                AccidentType.of(1, "Машина и человек"),
                "Accident: 1 car with bad road",
                "Ufa, Unknown road"));
        addAccident(Accident.of(
                "Ivan Ivanov",
                AccidentType.of(2, "Машина и велосипед"),
                "No information",
                "No information"));
        addAccident(Accident.of(
                "NASA",
                AccidentType.of(0, "Две машины"),
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
}
