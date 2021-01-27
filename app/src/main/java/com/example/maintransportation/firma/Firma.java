package com.example.maintransportation.firma;

public class Firma {

    private int imagineBus;
    private String numeSofer;
    private String nrMasina;
    private String ziPlecare;
    private String oraPlecare;
    private String numeFirma;

    public Firma(int imagineBus, String numeSofer, String nrMasina,
                 String ziPlecare, String oraPlecare, String numeFirma) {
        this.imagineBus = imagineBus;
        this.numeSofer = numeSofer;
        this.nrMasina = nrMasina;
        this.ziPlecare = ziPlecare;
        this.oraPlecare = oraPlecare;
        this.numeFirma = numeFirma;
    }

    public int getImagineBus() {
        return imagineBus;
    }

    public void setImagineBus(int imagineBus) {
        this.imagineBus = imagineBus;
    }

    public String getNumeSofer() {
        return numeSofer;
    }

    public void setNumeSofer(String numeSofer) {
        this.numeSofer = numeSofer;
    }

    public String getNrMasina() {
        return nrMasina;
    }

    public void setNrMasina(String nrMasina) {
        this.nrMasina = nrMasina;
    }

    public String getZiPlecare() {
        return ziPlecare;
    }

    public void setZiPlecare(String ziPlecare) {
        this.ziPlecare = ziPlecare;
    }

    public String getOraPlecare() {
        return oraPlecare;
    }

    public void setOraPlecare(String oraPlecare) {
        this.oraPlecare = oraPlecare;
    }

    public String getNumeFirma() {
        return numeFirma;
    }

    public void setNumeFirma(String numeFirma) {
        this.numeFirma = numeFirma;
    }

    @Override
    public String toString() {
        return "Firma{" +
                "numeSofer='" + numeSofer + '\'' +
                ", nrMasina='" + nrMasina + '\'' +
                ", ziPlecare='" + ziPlecare + '\'' +
                ", oraPlecare='" + oraPlecare + '\'' +
                ", numeFirma='" + numeFirma + '\'' +
                '}';
    }
}
