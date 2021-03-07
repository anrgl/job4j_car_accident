package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;
import ru.job4j.accident.repository.RuleHibernate;
import ru.job4j.accident.repository.TypeHibernate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class AccidentControl {
    private final AccidentHibernate accidents;
    private final RuleHibernate rules;
    private final TypeHibernate types;

    public AccidentControl(AccidentHibernate accidents, RuleHibernate rules, TypeHibernate types) {
        this.accidents = accidents;
        this.rules = rules;
        this.types = types;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAllAttributes(Map.of(
                "types", types.findAll(),
                "rules", rules.findAll()
        ));
        return "accident/create";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAllAttributes(Map.of(
                "accident", accidents.findById(id),
                "types", types.findAll(),
                "rules", rules.findAll()));
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        for (String id : ids) {
            Rule rule = rules.findById(Integer.parseInt(id));
            accident.addRule(rule);
        }
        accidents.save(accident);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident,
                         HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        for (String ruleId : ids) {
            Rule rule = rules.findById(Integer.parseInt(ruleId));
            accident.addRule(rule);
        }
        accidents.update(accident);
        return "redirect:/";
    }
}