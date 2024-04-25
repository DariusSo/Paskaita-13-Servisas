import java.io.*;
import java.util.*;

public class ServisoValdymasImpl implements ServisoValdymas{
    String pakaitiniaiPath = "C:\\Users\\Darius\\IdeaProjects\\Paskaita-13-Serviso-reg-sistema\\src\\PakaitiniaiAutomobiliai.csv";
    String suremontuotiPath = "C:\\Users\\Darius\\IdeaProjects\\Paskaita-13-Serviso-reg-sistema\\src\\SuremontuotuAutomobiliuIstorija.csv";
    String klientuAutoPath = "C:\\Users\\Darius\\IdeaProjects\\Paskaita-13-Serviso-reg-sistema\\src\\KlientaiIrJuAuto.csv";
    private List<Automobilis> remontuotiniList = new ArrayList<>();
    private HashMap<Klientas, List<Automobilis>> klientaiAutoMap = new HashMap<>();
    Scanner scanner = new Scanner(System.in);

    List<Klientas> klientaiList = new ArrayList<>();

    public void menu(){
        try{
            System.out.println("1 - Registruoti automobili remontui, 2 - Remontuojamu automobiliu sarasas, 3 - Grazinti klientui automobili, 4 - Kliento info" +
                    " 5 - Baigti.");
            int pasirinkimas = scanner.nextInt();
            scanner.nextLine();
            switch (pasirinkimas){
                case 1:
                    pridetiRemontui();
                    break;
                case 2:
                    for(Automobilis a : gautiVisąRemontuojamuAutoSarasa()){
                        System.out.println(a.toString());
                    }
                    break;
                case 3:
                    userGrazinamRemontuota();
                    break;
                case 4:
                    klientoMasinuSarasas();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Bloga ivestis. Bandykite dar karta.");
                    menu();
            }
        }catch (InputMismatchException e){
            scanner.next();
            System.out.println("Bloga ivestis.Bandykite dar karta.");
        }

    }
    @Override
    public void registruotiNaujaAutomobiliRemontui(Automobilis automobilis) {
        remontuotiniList.add(automobilis);
    }

    @Override
    public Automobilis suteiktiPakaitini() {
        Random random = new Random();
        try{
            FileReader fileReader = new FileReader(pakaitiniaiPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<Automobilis> tempList = new ArrayList<>();
            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] lineValues = line.split(",");
                Automobilis tempAuto = new PakaitinisAutomobilis(lineValues[0], lineValues[1],Integer.parseInt(lineValues[2]), kuroTipas.valueOf(lineValues[3].toUpperCase()), Integer.parseInt(lineValues[4]));
                tempList.add(tempAuto);
            }
            bufferedReader.close();
            fileReader.close();
            return tempList.get(random.nextInt(1,4));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Automobilis> gautiVisąRemontuojamuAutoSarasa() {
        return remontuotiniList;
    }
    public void grazintiKlientuiSuremontuota(Automobilis automobilis){
        try{
            System.out.println("Kliento vardas: ");
            String vardas = scanner.nextLine();
            FileWriter fileWriter = new FileWriter(suremontuotiPath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(automobilis.toString());
            for(Klientas k : klientaiList){
                if(k.equals(vardas)){
                    for(Automobilis a : k.klientoAuto){
                        if(a.equals(automobilis)){
                            k.pakaitinis = null;
                        }
                    }
                }
            }
            bufferedWriter.close();
            fileWriter.close();

        }catch (IOException e){

        }

    }

    public Automobilis sukurtRemontuiGrazinimui(){
        try{
            System.out.println("Automobilio marke:");
            String marke = scanner.nextLine();
            System.out.println("Automobilio modelis: ");
            String modelis = scanner.nextLine();
            System.out.println("Automobilio metai: ");
            int metai = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Kuro tipas: ");
            kuroTipas kuras = kuroTipas.valueOf(scanner.nextLine().toUpperCase());
            System.out.println("Defektai");
            String defektai = scanner.nextLine();
            Automobilis auto = new RemontuotinasAutomobilis(marke, modelis, metai, kuras, defektai);
            return auto;
        }catch (InputMismatchException e) {
            System.out.println("Blogi automobilio metai. Bandykite dar karta.");
            scanner.nextLine();
            sukurtRemontuiGrazinimui();
        }catch (IllegalArgumentException e){
            System.out.println("Blogai ivestas kuro tipas. Bandykite dar karta.");
            sukurtRemontuiGrazinimui();
        }
        return null;
    }
    public void KlientuSarasas() {
        try {
            FileReader reader = new FileReader(klientuAutoPath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Klientas klientas = null;
                String[] l = line.split(",");
                if(l.length > 8){
                    klientas = new VersloKlientas(Integer.parseInt(l[0]), l[1], l[2], l[3], suteiktiPakaitini(), l[8], l[9]);
                }else{
                    klientas = new Klientas(Integer.parseInt(l[0]), l[1], l[2], l[3], suteiktiPakaitini());
                }
                for(Klientas k : klientaiList){
                    if(k.pavarde.equals(klientas.pavarde)){
                        klientas = k;
                    }
                }
                Automobilis klientoAuto = new RemontuotinasAutomobilis(l[4], l[5], Integer.parseInt(l[6]), kuroTipas.valueOf(l[7].toUpperCase()), "Defektas");
                klientas.klientoAuto.add(klientoAuto);
                klientaiList.add(klientas);
                klientaiAutoMap.put(klientas, klientas.klientoAuto);
                remontuotiniList.add(klientoAuto);

            }
            bufferedReader.close();
            reader.close();
            System.out.println(klientaiAutoMap);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void userGrazinamRemontuota(){
        System.out.println("Automobilio modelis");
        String modelis = scanner.nextLine();
        int tikrinam = 0;
        Automobilis auto = null;
        for(Automobilis a : remontuotiniList){
            if (a.getModelis().equals(modelis)){
                grazintiKlientuiSuremontuota(a);
                if (a instanceof RemontuotinasAutomobilis){
                    ((RemontuotinasAutomobilis) a).setDefektai("Sutvarkyta");
                }
                System.out.println("Automobilis " + a + " sekmingai grazintas.");
                remontuotiniList.remove(a);
                tikrinam = 1;
                auto = a;
                break;
            }
        }
        if(tikrinam == 0){
            System.out.println("automobilis nerastas");
        }
    }
    public void klientoMasinuSarasas(){
        System.out.println("Klientos pavarde: ");
        String pavarde = scanner.nextLine();
        int tikrinam = 0;
        for (Klientas k : klientaiList){
            if(k.pavarde.equals(pavarde)){
                System.out.println(k);
                tikrinam = 1;
                for(Automobilis a : klientaiAutoMap.get(k)){
                    System.out.println(a);
                }
                break;
            }
        }
        if(tikrinam == 0){
            System.out.println("Klientas nerastas.");
        }
    }
    public void pridetiRemontui(){
            Automobilis auto = sukurtRemontuiGrazinimui();
            int fArJ = 0;
            try{
                System.out.println("Klientas yre fizinis(1) ar Juridinis(2) asmuo");
                fArJ = scanner.nextInt();
            }catch (InputMismatchException e){
                scanner.nextLine();
                System.out.println("Blogas pasirinkimas. Bandykite dar karte.");
                pridetiRemontui();
            }
            try{
                System.out.println("Kliento id: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Kliento vardas: ");
                String vardas = scanner.nextLine();
                System.out.println("Pavarde: ");
                String pavarde = scanner.nextLine();
                System.out.println("El pastas: ");
                String elPastas = scanner.nextLine();
                Klientas klientas = null;
                if (fArJ == 1){
                    klientas = new Klientas(id, vardas, pavarde, elPastas, suteiktiPakaitini());
                }else{
                    System.out.println("Imones pavadinimas: ");
                    String imone = scanner.nextLine();
                    System.out.println("PVM moketojo kodas: ");
                    String pvm = scanner.nextLine();
                    klientas = new VersloKlientas(id, vardas, pavarde, elPastas, suteiktiPakaitini(), imone, pvm);
                }
                klientas.klientoAuto.add(auto);
                klientaiList.add(klientas);
                klientaiAutoMap.put(klientas, klientas.klientoAuto);
                registruotiNaujaAutomobiliRemontui(auto);
            }catch (InputMismatchException e){
                scanner.nextLine();
                System.out.println("Blogai ivestas kliento id. Bandykite dar karta.");
                pridetiRemontui();
            }
    }
}
