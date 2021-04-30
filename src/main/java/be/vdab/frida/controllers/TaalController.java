package be.vdab.frida.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
@RequestMapping("talen")
class TaalController {
    private static final String[] TALEN ={"nl","en"};

    @GetMapping
    public ModelAndView talen(@RequestHeader("Accept-Language") String acceptLanguage){
        var modelAndView = new ModelAndView("talen");
        Arrays.stream(TALEN).filter(taal -> acceptLanguage.contains(taal))
                .findFirst().ifPresent(taal ->modelAndView.addObject("taal", taal));
        return modelAndView;
    }
}
