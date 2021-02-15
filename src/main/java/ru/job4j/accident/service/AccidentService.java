package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Map;

@Service
public class AccidentService {
    private final AccidentMem repository;

    public AccidentService(AccidentMem repository) {
        this.repository = repository;
    }

    public Map<Integer, Accident> findAll() {
        return repository.getAccidents();
    }

    public void create(Accident accident) {
        repository.addAccident(accident);
    }
}
