package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.*;

@Repository
public class RuleMem {
    private final Map<Integer, Rule> rules = new HashMap<>();

    private RuleMem() {
        rules.put(0, Rule.of(0, "Статья. 1"));
        rules.put(1, Rule.of(1, "Статья. 2"));
        rules.put(2, Rule.of(2, "Статья. 3"));
    }

    public Map<Integer, Rule> findAll() {
        return rules;
    }

    public Set<Rule> findByIds(String[] ids) {
        Set<Rule> result = new HashSet<>();
        for (String id : ids) {
            result.add(rules.get(Integer.parseInt(id)));
        }
        return result;
    }
}