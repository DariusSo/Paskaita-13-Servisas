public class PakaitinisAutomobilis extends Automobilis{
    int rida;
    public PakaitinisAutomobilis(String marke, String modelis, int metai, Enum kuroTipas, int rida) {
        super(marke, modelis, metai, kuroTipas);
        this.rida = rida;
    }
    @Override
    public String toCSV(){
        return getMarke() + "," + getModelis() + "," + getMetai() + "," + getKuroTipas() + "," + rida;
    }
    @Override
    public String toString(){
        return "Marke" + getMarke() + " | Modelis: " + getModelis() + " | Metai: " + getMetai() + " | Kuro tipas: " + getKuroTipas() + " | Rida: " + rida;
    }
}
