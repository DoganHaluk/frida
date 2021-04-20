package be.vdab.frida.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;

@RestController
@RequestMapping("/")
class IndexController {
    @GetMapping
    public String index() {
        var openOfGesloten = LocalDate.now().getDayOfWeek() == DayOfWeek.MONDAY ? "Gesloten" : "Open";
        return "<!DOCTYPE html>" +
                "<html lang='nl'>" +
                "  <head>" +
                "    <meta charset='UTF-8'>" +
                "    <title>Frituur Frida</title>" +
                "    <link rel='icon' href='images/frida.ico' type='image/x-icon'>" +
                "    <link rel='stylesheet' href='css/frida.css'>" +
                "  </head>" +
                "  <body>" +
                "    <h1>Frituur Frida</h1>"
                + openOfGesloten +
                "  </body>" +
                "</html>";
    }
}
