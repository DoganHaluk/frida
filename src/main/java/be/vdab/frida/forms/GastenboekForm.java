package be.vdab.frida.forms;

import java.time.LocalDate;

public class GastenboekForm {
    private final long id;
    private final String naam;
    private final LocalDate datum= LocalDate.now();
    private final String bericht;

    public GastenboekForm(long id, String naam, String bericht) {
        this.id = id;
        this.naam = naam;
        this.bericht = bericht;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public String getBericht() {
        return bericht;
    }
}
