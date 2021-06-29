package be.vdab.frida.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
public class ZoekDeFriet implements Serializable {
    private static final long serialVersionUID = 1L;
    private long geopendeDeur;

    public long getGeopendeDeur() {
        return geopendeDeur;
    }

    public void setGeopendeDeur(long geopendeDeur) {
        this.geopendeDeur = geopendeDeur;
    }
}
