package be.vdab.frida.controllers;

import be.vdab.frida.forms.BeginNaamForm;
import be.vdab.frida.services.SnackService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("snacks")
class SnackController {
    private final char[] alfabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private final SnackService snackService;

    SnackController(SnackService snackService) {
        this.snackService = snackService;
    }

    @GetMapping("alfabet")
    public ModelAndView alfabet() {
        return new ModelAndView("snackAlfabet", "alfabet", alfabet);
    }

    @GetMapping("alfabet/{letter}")
    public ModelAndView findByBeginletter(@PathVariable char letter) {
        return new ModelAndView("snackAlfabet", "alfabet", alfabet)
                .addObject("snacks", snackService.findByBeginNaam(String.valueOf(letter)));
    }

    @GetMapping("zoeksnack/form")
    public ModelAndView beginNaamForm() {
        return new ModelAndView("snackZoek").addObject(new BeginNaamForm(""));
    }

    @GetMapping("zoeksnack")
    public ModelAndView beginNaam(BeginNaamForm form, Errors errors) {
        var modelAndView = new ModelAndView("snackZoek");
        if (errors.hasErrors()) {
            return modelAndView;
        }
        return modelAndView.addObject("snacks", snackService.findByBeginNaam(form.getBeginNaam()));
    }
}

