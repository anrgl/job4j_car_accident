package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

@Controller
public class IndexControl {
    private final AccidentMem accidentMem = new AccidentMem();

    @GetMapping("/")
    public String index(Model model) {
        var accidents = accidentMem.getAccidents();
        accidentMem.addAccident(new Accident(0, "Test", "Text", "Address"));
        model.addAttribute("accidents", accidents);
        return "index";
    }
}
