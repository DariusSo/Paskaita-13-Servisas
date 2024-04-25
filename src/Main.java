public class Main {
    public static void main(String[] args) {
        ServisoValdymasImpl servisas = new ServisoValdymasImpl();
        servisas.KlientuSarasas();
        while(true){
            servisas.menu();
        }
    }
}
