package be.vdab.frida.controllers;

import be.vdab.frida.sessions.Deur;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("zoekDeFriet")
class FrietController {
    private final Deur deur;
    FrietController(Deur deur) {
        this.deur = deur;
    }

    @GetMapping
    public ModelAndView
    return new ModelAndView()
}
