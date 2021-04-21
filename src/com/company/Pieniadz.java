package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Pieniadz
{
    private double doZaplaty;
    private double reszta;
    private double pula;
    private boolean czyGotowka;
    Pieniadz(boolean czyGotowka, double doZaplaty)
    {
        this.doZaplaty = doZaplaty;
        this.reszta = 0;
        pula = 0;
        this.czyGotowka = czyGotowka;
    }
    public boolean zaplataZaBilety()
    {
        Scanner scanner = new Scanner(System.in);
        int wybor;
        ArrayList<Double> wSrodku = new ArrayList<Double>();
        doZaplaty = ((double)(int)(Math.round(doZaplaty * 100))) / 100.0;
        if(czyGotowka)
        {
            while(doZaplaty > pula) {
                System.out.println("do zaplaty : " + doZaplaty + "0zl");
                System.out.println("pula biletomatu : " + pula + "0zl");
                System.out.println("wrzuc monete");
                System.out.println("0 - anuluj");
                System.out.println("1 - 10gr");
                System.out.println("2 - 20gr");
                System.out.println("3 - 50gr");
                System.out.println("4 - 1zł");
                System.out.println("5 - 2zł");
                System.out.println("6 - 5zł");
                while(true) {
                    wybor = scanner.nextInt();
                    if (wybor == 0) {
                        System.out.println("transakcja anulowana");
                        if(wSrodku.get(0) != 0) {
                            System.out.println("zwracanie monet:");
                            for (int i = 0; i < wSrodku.size(); i++) {
                                if(wSrodku.get(i) < 1.0) System.out.println((int)(wSrodku.get(i) * 100)  + "gr");
                                else System.out.println(wSrodku.get(i).intValue() + "zl");
                            }
                        }
                        return false;
                    }
                    if (wybor == 1 || wybor == 2 || wybor == 3 || wybor == 4 || wybor == 5 || wybor == 6) break;
                    else System.out.println("wybierz poprawna opcje");
                }
                switch(wybor) {
                    case 1 -> wSrodku.add(0.1);
                    case 2 -> wSrodku.add(0.2);
                    case 3 -> wSrodku.add(0.5);
                    case 4 -> wSrodku.add(1.0);
                    case 5 -> wSrodku.add(2.0);
                    case 6 -> wSrodku.add(5.0);
                }
                pula = 0;
                for (int i = 0; i < wSrodku.size(); i++) pula += wSrodku.get(i);
                pula = ((double)(int)(Math.round(pula * 100))) / 100.0;
            }
            reszta = pula - doZaplaty;
            reszta = ((double)(int)(Math.round(reszta * 100))) / 100.0;
            if(reszta != 0) {
                if(reszta < 1) System.out.println("Reszta: " + (int)(reszta * 100) + "gr");
                else System.out.println("Reszta: " + reszta + "zl");
                double[] nominaly = {5.0, 2.0, 1.0, 0.5, 0.2, 0.1};
                System.out.println("Wyplacanie: ");
                int tmp = 0;
                while(reszta > 0) {
                    if(reszta >= nominaly[tmp]) {
                        reszta -= nominaly[tmp];
                        if(tmp >= 3) System.out.println((int)(nominaly[tmp] * 100) + "gr");
                        else System.out.println((int)nominaly[tmp] + "zl");
                    }
                    else if(tmp != 5) tmp++;
                    reszta=((double)(int)(Math.round(reszta * 100))) / 100.0;
                }
            }
        }
        else
        {
            System.out.println("do zaplaty : " + doZaplaty + "0zl");
            System.out.println("0 - anuluj");
            System.out.println("1 - zaplac karta");
            while(true) {
                wybor = scanner.nextInt();
                if (wybor == 1 || wybor == 2 ) break;
                else System.out.println("wybierz poprawna opcje");
            }
            switch(wybor) {
                case 1: {
                    System.out.println("pobrano z konta " + doZaplaty + "0zl");
                    return true;
                }
                case 2: {
                    System.out.println("transakcja anulowana");
                    return false;
                }
            }
        }
        return true;
    }

}
