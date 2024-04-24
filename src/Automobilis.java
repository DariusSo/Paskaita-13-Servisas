public class Automobilis {
    private String marke;
    private String modelis;

    private int metai;
    private Enum kuroTipas;

    public Automobilis(String marke, String modelis, int metai, Enum kuroTipas) {
        this.marke = marke;
        this.modelis = modelis;
        this.metai = metai;
        this.kuroTipas = kuroTipas;
    }


    public String toCSV() {
        return marke + "," + modelis + "," + metai + "," + kuroTipas;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModelis() {
        return modelis;
    }

    public void setModelis(String modelis) {
        this.modelis = modelis;
    }

    public int getMetai() {
        return metai;
    }

    public void setMetai(int metai) {
        this.metai = metai;
    }

    public Enum getKuroTipas() {
        return kuroTipas;
    }

    public void setKuroTipas(Enum kuroTipas) {
        this.kuroTipas = kuroTipas;
    }
}
