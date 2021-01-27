package com.example.maintransportation.client;

import java.io.Serializable;
import java.util.Date;

public class Client implements Serializable {
    private String numeClient;
    private String locPlecare;
    private String ziPlecare;
    private String oraPlecare;

    public Client(String numeClient, String locPlecare, String ziPlecare, String oraPlecare) {
        this.numeClient = numeClient;
        this.locPlecare = locPlecare;
        this.ziPlecare = ziPlecare;
        this.oraPlecare = oraPlecare;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public String getLocPlecare() {
        return locPlecare;
    }

    public void setLocPlecare(String locPlecare) {
        this.locPlecare = locPlecare;
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

    @Override
    public String toString() {
        return "Clientul: '" + numeClient + '\'' +
                ", locatia de plecare din: '" + locPlecare + '\'' +
                ", in ziua de: '" + ziPlecare + '\'' +
                ", la ora: '" + oraPlecare + '\'';
    }
}
