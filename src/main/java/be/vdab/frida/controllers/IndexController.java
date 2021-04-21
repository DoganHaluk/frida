package be.vdab.frida.controllers;

import be.vdab.frida.domain.Adres;
import be.vdab.frida.domain.Gemeente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Controller
@RequestMapping("/")
class IndexController {
    @GetMapping
    public ModelAndView index() {
        var openOfGesloten = LocalDate.now().getDayOfWeek() == DayOfWeek.MONDAY ? "gesloten" : "open";
        var modelAndView = new ModelAndView("index", "dag", openOfGesloten);
        modelAndView.addObject("zaakAdres", new Adres("Waaslandstraat", "51", new Gemeente("Hoboken", 2660)));
        return modelAndView;
    }
}
