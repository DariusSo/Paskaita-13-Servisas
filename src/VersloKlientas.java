public class VersloKlientas extends Klientas{
    String imonesPavadinimas;
    String PVMMoketojoKodas;

    public VersloKlientas(int id, String vardas, String pavarde, String elPastas, Automobilis pakaitinis, String imonesPavadinimas, String PVMMoketojoKodas) {
        super(id, vardas, pavarde, elPastas, pakaitinis);
        this.imonesPavadinimas = imonesPavadinimas;
        this.PVMMoketojoKodas = PVMMoketojoKodas;
    }
    @Override
    public String toString(){
        return "Vardas: " + vardas + " | Pavarde: " + pavarde + " | El. pastas: " + elPastas + " | Pakaitinis: " + pakaitinis + " | PVMMoketojoKodas" + PVMMoketojoKodas;
    }
}
