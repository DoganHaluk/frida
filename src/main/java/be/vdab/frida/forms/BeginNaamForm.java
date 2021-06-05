package be.vdab.frida.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class BeginNaamForm {
    @NotEmpty
    @NotBlank
    private final String beginNaam;

    public BeginNaamForm(String beginNaam) {
        this.beginNaam = beginNaam;
    }

    public String getBeginNaam() {
        return beginNaam;
    }
}
