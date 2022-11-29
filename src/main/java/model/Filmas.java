package model;

public class Filmas {
    int id;
    String pavadinimas;
    int trukme;
    double ivertinimas;
    String aprasymas;

    public Filmas() {
    }

    public Filmas(int id, String pavadinimas, int trukme, double ivertinimas, String aprasymas) {
        this.id = id;
        this.pavadinimas = pavadinimas;
        this.trukme = trukme;
        this.ivertinimas = ivertinimas;
        this.aprasymas = aprasymas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public int getTrukme() {
        return trukme;
    }

    public void setTrukme(int trukme) {
        this.trukme = trukme;
    }

    public double getIvertinimas() {
        return ivertinimas;
    }

    public void setIvertinimas(double ivertinimas) {
        this.ivertinimas = ivertinimas;
    }

    public String getAprasymas() {
        return aprasymas;
    }

    public void setAprasymas(String aprasymas) {
        this.aprasymas = aprasymas;
    }

    @Override
    public String toString() {
        return "Filmas{" +
                "id=" + id +
                ", pavadinimas='" + pavadinimas + '\'' +
                ", trukme=" + trukme +
                ", ivertinimas=" + ivertinimas +
                ", aprasymas='" + aprasymas + '\'' +
                '}';
    }
}
