import java.util.List;

public interface ServisoValdymas {
    void registruotiNaujaAutomobiliRemontui(Automobilis automobilis);
    Automobilis suteiktiPakaitini();

    List<Automobilis> gautiVisąRemontuojamuAutoSarasa();
    void grazintiKlientuiSuremontuota(Automobilis automobilis);


}
