package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.RuleRepository;
import ru.job4j.accident.repository.TypeRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

@Controller
public class AccidentControl {
    private final AccidentRepository accidents;
    private final RuleRepository rules;
    private final TypeRepository types;

    public AccidentControl(AccidentRepository accidents,
                           RuleRepository rules,
                           TypeRepository types) {
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
        Optional<Accident> accOpt = accidents.findById(id);
        if (accOpt.isPresent()) {
            model.addAllAttributes(Map.of(
                    "accident", accOpt.get(),
                    "types", types.findAll(),
                    "rules", rules.findAll()));
            return "accident/edit";
        }
        return "error/404";
    }

    @PostMapping("/save")
    @Transactional
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        for (String id : ids) {
            Optional<Rule> rule = rules.findById(Integer.parseInt(id));
            rule.ifPresent(accident::addRule);
        }
        accidents.save(accident);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident,
                         HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        for (String ruleId : ids) {
            Rule rule = rules.findById(Integer.parseInt(ruleId)).orElseThrow();
            accident.addRule(rule);
        }
        accidents.save(accident);
        return "redirect:/";
    }
}