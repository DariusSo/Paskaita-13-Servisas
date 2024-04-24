public class RemontuotinasAutomobilis extends Automobilis{
    String defektai;
    public RemontuotinasAutomobilis(String marke, String modelis, int metai, Enum kuroTipas, String defektai) {
        super(marke, modelis, metai, kuroTipas);
        this.defektai = defektai;
    }
    @Override
    public String toCSV(){
        return getMarke() + "," + getModelis() + "," + getMetai() + "," + getKuroTipas() + "," + defektai;
    }
    @Override
    public String toString(){
        return "Marke" + getMarke() + " | Modelis: " + getModelis() + " | Metai: " + getMetai() + " | Kuro tipas: " + getKuroTipas() + " | Defektai: " + defektai;
    }
}
