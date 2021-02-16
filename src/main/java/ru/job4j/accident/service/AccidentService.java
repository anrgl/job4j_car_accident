package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.repository.AccidentTypeMem;
import ru.job4j.accident.repository.RuleMem;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@Service
public class AccidentService {
    private final AccidentMem accidentRepository;
    private final AccidentTypeMem accidentTypeRepository;
    private final RuleMem ruleRepository;

    public AccidentService(AccidentMem accRep, AccidentTypeMem accTypeRep, RuleMem ruleRep) {
        this.accidentRepository = accRep;
        this.accidentTypeRepository = accTypeRep;
        this.ruleRepository = ruleRep;
    }

    public Map<Integer, Accident> findAll() {
        return accidentRepository.getAccidents();
    }

    public void create(Accident accident) {
        accidentRepository.addAccident(accident);
    }

    public Accident findById(int id) {
        return accidentRepository.findById(id);
    }

    public void update(int id, Accident accident) {
        accidentRepository.updateAccident(id, accident);
    }

    public Map<Integer, AccidentType> findAllAccidentTypes() {
        return accidentTypeRepository.findAllAccidentTypes();
    }

    public AccidentType findAccidentTypeById(int typeId) {
        return accidentTypeRepository.findAccidentTypeById(typeId);
    }

    public Map<Integer, Rule> findAllRules() {
        return ruleRepository.findAll();
    }

    public Set<Rule> findAllRulesByIds(String[] ids) {
        return ruleRepository.findByIds(
                Arrays.stream(ids)
                        .mapToInt(Integer::parseInt)
                        .toArray());
    }

    public void saveAccident(@ModelAttribute Accident accident, String[] ids) {
        int typeId = accident.getType().getId();
        var type = findAccidentTypeById(typeId);
        Set<Rule> rules = findAllRulesByIds(ids);
        accident.setType(type);
        accident.setRules(rules);
    }
}
