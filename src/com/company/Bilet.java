package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bilet extends Cena
{
    private Date data;
    private String dataString;
    private String rodzaj;
    private double cena;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Bilet(String rodzaj)
    {
        data = new Date();
        dataString = sdf.format(data);
        this.rodzaj = rodzaj;
        switch (rodzaj) {
            case "ulgowy20" -> this.cena = getUlgowy20min();
            case "ulgowy60" -> this.cena = getUlgowy60min();
            case "ulgowy90" -> this.cena = getUlgowy90min();
            case "normalny20" -> this.cena = getNormalny20min();
            case "normalny60" -> this.cena = getNormalny60min();
            case "normalny90" -> this.cena = getNormalny90min();
            default -> System.exit(0);
        }
    }
    public Date getData() {
        return data;
    }
    public String getDataString() {
        return dataString;
    }
    public String getRodzaj() {
        return rodzaj;
    }
    public double getCena() {
        return cena;
    }
    public String toString(){
        return data + " " + rodzaj + " " +  cena + "0zl";
    }
}
