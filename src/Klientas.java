import java.util.List;

public class Klientas {
    int id;
    String vardas;
    String pavarde;
    String elPastas;
    Automobilis pakaitinis;
    List<Automobilis> klientoAuto;

    public Klientas(int id, String vardas, String pavarde, String elPastas, Automobilis pakaitinis) {
        this.id = id;
        this.vardas = vardas;
        this.pavarde = pavarde;
        this.elPastas = elPastas;
        this.pakaitinis = pakaitinis;
    }
}
