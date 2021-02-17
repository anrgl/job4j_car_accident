package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into accident (name, text, address, type_id) values (?, ?, ?, ?)";
        jdbc.update(cn -> {
            PreparedStatement ps = cn.prepareStatement(
                    sql, new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.setId((int) keyHolder.getKey());
        return accident;
    }

    public Accident update(int id, Accident accident) {
        jdbc.update(
                "update accident set name = ?, text = ?, address = ?, type_id = ? where id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                id);
        return accident;
    }

    public Accident findAccidentById(int id) {
        return jdbc.queryForObject("select name, text, address, type_id from accident where id = ?",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(id);
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(findAccidentTypeById(rs.getInt("type_id")));
                    return accident;
                }, id);
    }

    public List<Accident> getAll() {
        return jdbc.query("select id, name, text, address, type_id from accident order by id",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(findAccidentTypeById(rs.getInt("type_id")));
                    accident.setRules(findRulesByAccidentId(rs.getInt("id")));
                    return accident;
                });
    }

    public void saveAccidentRule(int accidentId, String[] ids) {
        for (String id : ids) {
            jdbc.update("insert into accident_rule (accident_id, rule_id) values (?, ?)",
                    accidentId,
                    Integer.parseInt(id));
        }
    }

    public void deleteAccidentRule(int accidentId) {
        jdbc.update("delete from accident_rule where accident_id = ?", accidentId);
    }

    public List<AccidentType> findAllAccidentTypes() {
        return jdbc.query("select id, name from type",
                (rs, row) -> AccidentType.of(rs.getInt("id"), rs.getString("name")));
    }

    public AccidentType findAccidentTypeById(int id) {
        return jdbc.queryForObject("select id, name from type where id = ?",
                (rs, row) -> AccidentType.of(rs.getInt("id"), rs.getString("name")),
                id);
    }

    public Set<Rule> findRulesByAccidentId(int id) {
        return new HashSet<>(jdbc.query(
                "select r.id, r.name from accident_rule as ar"
                        + " join accident ac on ar.accident_id = ac.id"
                        + " join rule r on ar.rule_id = r.id where ac.id = ?",
                (rs, row) -> Rule.of(rs.getInt("id"), rs.getString("name")),
                id));
    }

    public Set<Rule> findAllRules() {
        return new HashSet<>(jdbc.query("select id, name from rule",
                (rs, row) -> Rule.of(rs.getInt("id"), rs.getString("name"))));
    }
}
