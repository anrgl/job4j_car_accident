package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(0);
    private final Map<Integer, Accident> accidents = new HashMap<>();

    private AccidentMem() {

        addAccident(new Accident(
                "Irina Sorokina",
                AccidentType.of(0, "Две машины"),
                Set.of(Rule.of(0, "Статья. 1"), Rule.of(1, "Статья. 2")),
                "Accident: 2 cars",
                "Moscow, Lenin street"));
        addAccident(new Accident(
                "Oleg Victorovich",
                AccidentType.of(1, "Машина и человек"),
                Set.of(Rule.of(1, "Статья. 2"), Rule.of(2, "Статья. 42")),
                "Accident: 1 car with bad road",
                "Ufa, Unknown road"));
        addAccident(new Accident(
                "Ivan Ivanov",
                AccidentType.of(2, "Машина и велосипед"),
                Set.of(Rule.of(0, "Статья. 1")),
                "No information",
                "No information"));
        addAccident(new Accident(
                "NASA",
                AccidentType.of(0, "Две машины"),
                Set.of(Rule.of(1, "Статья. 2")),
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
