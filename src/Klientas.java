import java.util.ArrayList;
import java.util.List;

public class Klientas {
    int id;
    String vardas;
    String pavarde;
    String elPastas;
    Automobilis pakaitinis;
    List<Automobilis> klientoAuto = new ArrayList<>();

    public Klientas(int id, String vardas, String pavarde, String elPastas, Automobilis pakaitinis) {
        this.id = id;
        this.vardas = vardas;
        this.pavarde = pavarde;
        this.elPastas = elPastas;
        this.pakaitinis = pakaitinis;
    }
    @Override
    public String toString(){
        return "Vardas: " + vardas + " | Pavarde: " + pavarde + " | El. pastas: " + elPastas + " | Pakaitinis: " + pakaitinis;
    }
}
