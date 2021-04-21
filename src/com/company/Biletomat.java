package com.company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Biletomat extends Cena
{
    private List<Bilet> aktualnaTransakcja;
    private List <Bilet> listaBiletow;
    private String lokalizacja;
    Biletomat(String lokalizacja)
    {
        aktualnaTransakcja = new ArrayList<>();
        listaBiletow = new ArrayList<>();
        this.lokalizacja = lokalizacja;
    }
    public static boolean porownanieString(String nap1, String nap2)
    {
        int s1 = nap1.length();
        int s2 = nap2.length();
        if(s1 != s2) return false;
        else for(int i = 0; i < s1; i++) if(nap1.charAt(i) != nap2.charAt(i)) return false;
        return true;
    }
    public void wydruk()
    {
        System.out.println("podaj date (dd-mm-yyyy)");
        Scanner scan = new Scanner(System.in);
        String nap = scan.nextLine();
        wydrukujTransakcje(nap);
    }
    public void wydrukujTransakcje(String lokalnaData)
    {
        for(int i = 0; i < listaBiletow.size(); i++) if(porownanieString(lokalnaData, listaBiletow.get(i).getDataString())) System.out.println(listaBiletow.get(i));
    }
    public void zaplata()
    {
        double cenaBiletow = 0;
        for(int i = 0; i < aktualnaTransakcja.size(); i++) cenaBiletow += aktualnaTransakcja.get(i).getCena();
        int wybor;
        Scanner scanner = new Scanner(System.in);
        System.out.println("wybierz rodzaj zaplaty");
        System.out.println("0 - powrot");
        System.out.println("1 - gotowka");
        System.out.println("2 - bezgotowkowa");
        while(true) {
            wybor = scanner.nextInt();
            if (wybor == 0) {
                czyZaplacic();
                return;
            }
            if (wybor == 1 || wybor == 2 ) break;
            else System.out.println("wybierz poprawna opcje");
        }
        Pieniadz pieniadz = null;
        switch (wybor) {
            case 1 -> pieniadz = new Pieniadz(true,cenaBiletow);
            case 2 -> pieniadz = new Pieniadz(false,cenaBiletow);
        }
        if(pieniadz.zaplataZaBilety())
        {
            System.out.println("Transakcja pomyslnie zrealizowana");
            for(int i = 0; i < aktualnaTransakcja.size(); i++) {
                listaBiletow.add(aktualnaTransakcja.get(i));
                System.out.println(aktualnaTransakcja.get(i));
            }
        }
        aktualnaTransakcja.clear();
    }
    public void transakcja()
    {
        int wybor;
        Scanner scanner = new Scanner(System.in);
        System.out.println("wybierz bilet do kupienia");
        System.out.println("0 - powrot");
        System.out.println("1 - ulgowy 20min - " + getUlgowy20min() + "0zł");
        System.out.println("2 - ulgowy 60min - " + getUlgowy60min() + "0zł");
        System.out.println("3 - ulgowy 90min - " + getUlgowy90min() + "0zł");
        System.out.println("4 - normalny 20min - " + getNormalny20min() + "0zł");
        System.out.println("5 - normalny 60min - " + getNormalny60min() + "0zł");
        System.out.println("6 - normalny 90min - " + getNormalny90min() + "0zł");
        while(true) {
            wybor = scanner.nextInt();
            if (wybor == 0) return;
            if (wybor == 1 || wybor == 2 || wybor == 3 || wybor == 4 || wybor == 5 || wybor == 6) break;
            else System.out.println("wybierz poprawna opcje");
        }
        Date data = new Date();
        SimpleDateFormat simpleData = new SimpleDateFormat("dd-MM-yyyy");
        Bilet bilet = null;
        switch (wybor) {
            case 1 -> bilet = new Bilet("ulgowy20");
            case 2 -> bilet = new Bilet("ulgowy60");
            case 3 -> bilet = new Bilet("ulgowy90");
            case 4 -> bilet = new Bilet("normalny20");
            case 5 -> bilet = new Bilet("normalny60");
            case 6 -> bilet = new Bilet("normalny90");
        }
        aktualnaTransakcja.add(bilet);
        czyZaplacic();
    }
    public void czyZaplacic()
    {
        int wybor;
        Scanner scanner = new Scanner(System.in);
        System.out.println("0 - powrot");
        System.out.println("1 - zaplata");
        System.out.println("2 - kolejny bilet");
        while(true) {
            wybor = scanner.nextInt();
            if (wybor == 0) {
                aktualnaTransakcja.clear();
                transakcja();
                return;
            }
            if (wybor == 1 || wybor == 2) break;
            else System.out.println("wybierz poprawna opcje");
        }
        switch (wybor) {
            case 1 -> zaplata();
            case 2 -> transakcja();
        }
    }
    public String toString()
    {
        if (listaBiletow.size() == 0) return "Brak sprzedanych biletow";
        else {
            String zwrot;
            String data = listaBiletow.get(0).getDataString();
            int u20 = 0;
            int u60 = 0;
            int u90 = 0;
            int n20 = 0;
            int n60 = 0;
            int n90 = 0;
            zwrot = "lokalizacja : " + lokalizacja + "\n";
            for (int i = 0; i < listaBiletow.size(); i++) {
                if ((listaBiletow.size() - 1 == i) || (!porownanieString(data, listaBiletow.get(i).getDataString()))) {
                    String nap = listaBiletow.get(i).getRodzaj();
                    switch (nap) {
                        case "ulgowy20" -> u20++;
                        case "ulgowy60" -> u60++;
                        case "ulgowy90" -> u90++;
                        case "normalny20" -> n20++;
                        case "normalny60" -> n60++;
                        case "normalny90" -> n90++;
                    }
                    if (u20 != 0) {
                        double dochod = u20 * getUlgowy20min();
                        dochod = ((double) (int) (Math.round(dochod * 100))) / 100.0;
                        zwrot = zwrot + data + " : ulgowy20min : " + u20 + "szt : " + dochod + "0zl" + "\n";
                        u20 = 0;
                    }
                    if (u60 != 0) {
                        double dochod = u60 * getUlgowy60min();
                        dochod = ((double) (int) (Math.round(dochod * 100))) / 100.0;
                        zwrot = zwrot + data + " : ulgowy60min : " + u60 + "szt : " + dochod + "0zl" + "\n";
                        u60 = 0;
                    }
                    if (u90 != 0) {
                        double dochod = u90 * getUlgowy90min();
                        dochod = ((double) (int) (Math.round(dochod * 100))) / 100.0;
                        zwrot = zwrot + data + " : ulgowy90min : " + u90 + "szt : " + dochod + "0zl" + "\n";
                        u90 = 0;
                    }
                    if (n20 != 0) {
                        double dochod = n20 * getNormalny20min();
                        dochod = ((double) (int) (Math.round(dochod * 100))) / 100.0;
                        zwrot = zwrot + data + " : normalny20min : " + n20 + "szt : " + dochod + "0zl" + "\n";
                        n20 = 0;
                    }
                    if (n60 != 0) {
                        double dochod = n60 * getNormalny60min();
                        dochod = ((double) (int) (Math.round(dochod * 100))) / 100.0;
                        zwrot = zwrot + data + " : normalny60min : " + n60 + "szt : " + dochod + "0zl" + "\n";
                        n60 = 0;
                    }
                    if (n90 != 0) {
                        double dochod = n90 * getNormalny90min();
                        dochod = ((double) (int) (Math.round(dochod * 100))) / 100.0;
                        zwrot = zwrot + data + " : normalny90min : " + n90 + "szt : " + dochod + "0zl" + "\n";
                        n90 = 0;
                    }
                    data = listaBiletow.get(i).getDataString();
                }
                String nap = listaBiletow.get(i).getRodzaj();
                switch (nap) {
                    case "ulgowy20" -> u20++;
                    case "ulgowy60" -> u60++;
                    case "ulgowy90" -> u90++;
                    case "normalny20" -> n20++;
                    case "normalny60" -> n60++;
                    case "normalny90" -> n90++;
                }
            }
            return zwrot;
        }
    }
}