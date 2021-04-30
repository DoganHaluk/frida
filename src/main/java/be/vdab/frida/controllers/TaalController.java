package be.vdab.frida.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("talen")
class TaalController {

    @GetMapping
    public ModelAndView nederlands(@RequestHeader("Accept-Language") String acceptLanguage){
        return new ModelAndView("talen", "nederlands", acceptLanguage.startsWith("nl"));
    }
}
