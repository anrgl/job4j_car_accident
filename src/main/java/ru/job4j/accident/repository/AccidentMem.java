package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private int size = accidents.size();

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
        accidents.put(size++, accident);
    }

    public Map<Integer, Accident> getAccidents() {
        return accidents;
    }
}
