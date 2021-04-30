package be.vdab.frida.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.standard.processor.StandardAltTitleTagProcessor;

import java.util.Arrays;

@Controller
@RequestMapping("talen")
class TaalController {
    String geen="geen";
    @GetMapping
    public ModelAndView talen(@RequestHeader("Accept-Language") String acceptLanguage){
        var modelAndView = new ModelAndView("talen");
        if(!acceptLanguage.contains("nl")) {
            modelAndView.addObject("taal", geen);
        }
        return modelAndView;
    }
}
