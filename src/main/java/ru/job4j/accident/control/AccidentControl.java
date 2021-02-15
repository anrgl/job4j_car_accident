package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

@Controller
public class AccidentControl {
    private final AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        var types = service.findAllAccidentTypes();
        model.addAttribute("types", types);
        return "accident/create";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        var types = service.findAllAccidentTypes();
        var accident = service.findById(id);
        model.addAttribute("accident", accident);
        model.addAttribute("types", types);
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        int typeId = accident.getType().getId();
        var type = service.findAccidentTypeById(typeId);
        accident.setType(type);
        service.create(accident);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(int id, @ModelAttribute Accident accident) {
        int typeId = accident.getType().getId();
        var type = service.findAccidentTypeById(typeId);
        accident.setType(type);
        service.update(id, accident);
        return "redirect:/";
    }
}
