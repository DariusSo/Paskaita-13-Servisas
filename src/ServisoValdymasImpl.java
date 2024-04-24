import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ServisoValdymasImpl implements ServisoValdymas{
    String pakaitiniaiPath = "C:\\Users\\Darius\\IdeaProjects\\Paskaita-13-Serviso-reg-sistema\\src\\PakaitiniaiAutomobiliai.csv";
    String suremontuotiPath = "C:\\Users\\Darius\\IdeaProjects\\Paskaita-13-Serviso-reg-sistema\\src\\SuremontuotuAutomobiliuIstorija.csv";
    String klientuAutoPath = "C:\\Users\\Darius\\IdeaProjects\\Paskaita-13-Serviso-reg-sistema\\src\\KlientaiIrJuAuto.csv";
    private List<Automobilis> remontuotiniList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    List<Klientas> klientaiList = new ArrayList<>();

    public void menu(){
        System.out.println("1 - Registruoti automobili remontui, 2 - Remontuojamu automobiliu sarasas, 3 - Grazinti klientui automobili" +
                " 4 - suteikti pakaitini");
        int pasirinkimas = scanner.nextInt();
        scanner.nextLine();
        switch (pasirinkimas){
            case 1:
                registruotiNaujaAutomobiliRemontui(sukurtRemontuiGrazinimui());
                break;
            case 2:
                for(Automobilis a : gautiVisąRemontuojamuAutoSarasa()){
                    System.out.println(a.toString());
                }
                break;
            case 3:
                System.out.println("Automobilio modelis");
                String modelis = scanner.nextLine();
                int tikrinam = 0;
                for(Automobilis a : remontuotiniList){
                    if (a.getModelis().equals(modelis)){
                        grazintiKlientuiSuremontuota(a);
                        System.out.println("Automobilis " + a.toString() + " sekmingai grazintas.");
                        remontuotiniList.remove(a);
                        tikrinam = 1;
                        break;
                    }
                }
                if(tikrinam == 0){
                    System.out.println("automobilis nerastas");
                }
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
                Automobilis tempAuto = new PakaitinisAutomobilis(lineValues[0], lineValues[1],Integer.parseInt(lineValues[2]), kuroTipas.valueOf(lineValues[3]), Integer.parseInt(lineValues[4]));
            }
            bufferedReader.close();
            fileReader.close();
            return tempList.get(random.nextInt());
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
            FileWriter fileWriter = new FileWriter(suremontuotiPath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(automobilis.toString());
            bufferedWriter.close();
            fileWriter.close();

        }catch (IOException e){

        }

    }

    public Automobilis sukurtRemontuiGrazinimui(){
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
    }
    public void KlientuSarasas(){
        try{
            FileReader reader = new FileReader(klientuAutoPath);
            BufferedReader BufferedReader = new BufferedReader(reader);
        }catch (IOException e){

        }

    }

}
