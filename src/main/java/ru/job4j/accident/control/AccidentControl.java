package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class AccidentControl {
    private final AccidentJdbcTemplate accidents;

    public AccidentControl(AccidentJdbcTemplate accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        var types = accidents.findAllAccidentTypes();
        var rules = accidents.findAllRules();
        model.addAllAttributes(Map.of(
                "types", types,
                "rules", rules));
        return "accident/create";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        var types = accidents.findAllAccidentTypes();
        var accident = accidents.findAccidentById(id);
        var rules = accidents.findAllRules();
        model.addAllAttributes(Map.of(
                "accident", accident,
                "types", types,
                "rules", rules));
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        int accidentId = accidents.save(accident).getId();
        accidents.saveAccidentRule(accidentId, ids);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") int id,
                         @ModelAttribute Accident accident,
                         HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        accidents.update(id, accident);
        accidents.deleteAccidentRule(id);
        accidents.saveAccidentRule(id, ids);
        return "redirect:/";
    }
}