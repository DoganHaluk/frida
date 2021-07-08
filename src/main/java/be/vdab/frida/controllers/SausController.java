package be.vdab.frida.controllers;

import be.vdab.frida.forms.SausRadenForm;
import be.vdab.frida.services.SausService;
import be.vdab.frida.sessions.RaadDeSaus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@RequestMapping("sauzen")
class SausController {
    private final char[] alfabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private final SausService sausService;
    private final RaadDeSaus raadDeSaus;

    public SausController(SausService sausService, RaadDeSaus raadDeSaus) {
        this.sausService = sausService;
        this.raadDeSaus = raadDeSaus;
    }

    @GetMapping
    public ModelAndView sauzen() {
        return new ModelAndView("sauzen", "sauzen", sausService.findAll());
    }

    @GetMapping("{id}")
    public ModelAndView saus(@PathVariable long id) {
        var modelAndView = new ModelAndView("saus");
        sausService.findById(id)
                .ifPresent(saus -> modelAndView.addObject(saus));
        return modelAndView;
    }

    @GetMapping("alfabet")
    public ModelAndView alfabet() {
        return new ModelAndView("sausAlfabet", "alfabet", alfabet);
    }

    @GetMapping("alfabet/{letter}")
    public ModelAndView sauzenBeginnendMet(@PathVariable char letter) {
        return new ModelAndView("sausAlfabet", "alfabet", alfabet)
                .addObject("sauzen", sausService.findByNaamBegintMet(letter));
    }

    private String randomSaus() {
        var sauzen = sausService.findAll();
        return sauzen.get(ThreadLocalRandom.current().nextInt(sauzen.size())).getNaam();
    }

    @GetMapping("raden")
    public ModelAndView radenForm() {

        return new ModelAndView("sausRaden").addObject(raadDeSaus).addObject(new SausRadenForm(null));
    }

    @PostMapping("raden/volgendegok")
    public ModelAndView raden(@Valid SausRadenForm form, Errors errors) {
        if (errors.hasErrors()) {
            return new ModelAndView("sausRaden").addObject(raadDeSaus);
        }
        raadDeSaus.gok(form.getLetter());
        raadDeSaus.voegLetterToe(form.getLetter());
        return new ModelAndView("redirect:/sauzen/raden");
    }

    @PostMapping("raden/nieuwspel")
    public String radenNieuwSpel() {
        raadDeSaus.reset(randomSaus());
        return "redirect:/sauzen/raden";
    }
}
