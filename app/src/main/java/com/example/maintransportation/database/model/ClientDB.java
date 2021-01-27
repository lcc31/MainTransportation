package com.example.maintransportation.database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.maintransportation.database.util.DateConverter;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "clientidb")
public class ClientDB implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "numeClient")
    private String numeClient;
    @ColumnInfo(name = "locPlecare")
    private String locPlecare;
    @ColumnInfo(name = "ziPlecare")
    private Date ziPlecare;
    @ColumnInfo(name = "oraPlecare")
    private String oraPlecare;
    @ColumnInfo(name = "firma")
    private String firma;

    public ClientDB(long id, String numeClient, String locPlecare,
                    Date ziPlecare, String oraPlecare, String firma) {
        this.id = id;
        this.numeClient = numeClient;
        this.locPlecare = locPlecare;
        this.ziPlecare = ziPlecare;
        this.oraPlecare = oraPlecare;
        this.firma = firma;
    }

    @Ignore
    public ClientDB(String numeClient, String locPlecare,
                    Date ziPlecare, String oraPlecare, String firma) {
        this.numeClient = numeClient;
        this.locPlecare = locPlecare;
        this.ziPlecare = ziPlecare;
        this.oraPlecare = oraPlecare;
        this.firma = firma;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getZiPlecare() {
        return ziPlecare;
    }

    public void setZiPlecare(Date ziPlecare) {
        this.ziPlecare = ziPlecare;
    }

    public String getOraPlecare() {
        return oraPlecare;
    }

    public void setOraPlecare(String oraPlecare) {
        this.oraPlecare = oraPlecare;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    @Override
    public String toString() {
        return "ClientDB{" +
                "numeClient='" + numeClient + '\'' +
                ", locPlecare='" + locPlecare + '\'' +
                ", ziPlecare=" + DateConverter.fromDate(ziPlecare) +
                ", oraPlecare='" + oraPlecare + '\'' +
                ", firma='" + firma + '\'' +
                '}';
    }
}