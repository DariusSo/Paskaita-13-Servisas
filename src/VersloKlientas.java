public class VersloKlientas extends Klientas{
    String imonesPavadinimas;
    String imonesKodas;
    String PVMMoketojoKodas;

    public VersloKlientas(int id, String vardas, String pavarde, String elPastas, Automobilis pakaitinis, String imonesPavadinimas, String imonesKodas, String PVMMoketojoKodas) {
        super(id, vardas, pavarde, elPastas, pakaitinis);
        this.imonesPavadinimas = imonesPavadinimas;
        this.imonesKodas = imonesKodas;
        this.PVMMoketojoKodas = PVMMoketojoKodas;
    }
}
