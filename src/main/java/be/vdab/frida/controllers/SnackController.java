package be.vdab.frida.controllers;

import be.vdab.frida.services.SnackService;
import org.springframework.stereotype.Controller;
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
    @GetMapping
    public ModelAndView snacks() {
        return new ModelAndView("snacks", "snacks", snackService.findAll());
    }

    @GetMapping("{id}")
    public ModelAndView snack(@PathVariable long id) {
        var modelAndView = new ModelAndView("snack");
        snackService.findById(id)
                .ifPresent(snack -> modelAndView.addObject(snack));
        return modelAndView;
    }

    @GetMapping("alfabet")
    public ModelAndView alfabet() {
        return new ModelAndView("sausAlfabet", "alfabet", alfabet);
    }

    @GetMapping("alfabet/{letter}")
    public ModelAndView sauzenBeginnendMet(@PathVariable char letter) {
        return new ModelAndView("sausAlfabet", "alfabet", alfabet)
                .addObject("sauzen", snackService.findByBeginNaam(beginNaam));
    }
}

