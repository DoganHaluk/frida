package be.vdab.frida.controllers;

import be.vdab.frida.forms.GastenBoekEntryForm;
import be.vdab.frida.services.GastenBoekService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("gastenboek")
public class GastenBoekController {
    private final GastenBoekService gastenBoekService;

    public GastenBoekController(GastenBoekService gastenBoekService) {
        this.gastenBoekService = gastenBoekService;
    }

    @GetMapping
    public ModelAndView findAll() {
        return new ModelAndView("gastenboek", "gastenboek", gastenBoekService.findAll());
    }

    @GetMapping("toevoegen/form")
    public ModelAndView toevoegForm() {
        return new ModelAndView("gastenboek", "gastenboek", gastenBoekService.findAll()).addObject(new GastenBoekEntryForm("", ""));
    }

    @PostMapping("toevoegen")
    public ModelAndView toevoegen(@Valid GastenBoekEntryForm form, Errors errors) {
        if (errors.hasErrors()) {
            return new ModelAndView("gastenboek", "gastenboek", gastenBoekService.findAll());
        }
        gastenBoekService.create(form);
        return new ModelAndView("redirect:/gastenboek");
    }
}
