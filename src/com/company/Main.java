package com.company;

import java.util.Scanner;

class Main {

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Biletomat b = new Biletomat("Kraków");
        int wybor;
        while(true)
        {
            System.out.println("0 - zakoncz");
            System.out.println("1 - zakup biletu");
            System.out.println("2 - wydruk sprzedanych biletow");
            System.out.println("3 - wydruk transakcji z okreslonej daty");
            while(true) {
                wybor = scanner.nextInt();
                if (wybor == 0 || wybor == 1 || wybor == 2 || wybor == 3) break;
                else System.out.println("wybierz poprawna opcje");
            }
            switch(wybor) {
                case 0 -> System.exit(0);
                case 1 -> b.transakcja();
                case 2 -> System.out.println(b);
                case 3 -> b.wydruk();
            }
        }
    }
}
