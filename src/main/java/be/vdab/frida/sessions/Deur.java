package be.vdab.frida.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
public class Deur implements Serializable {
    private static final long serialVersionUID = 1L;
    private int deur;

    public int getDeur() {
        return deur;
    }

    public void setDeur(int deur) {
        this.deur = deur;
    }
}
