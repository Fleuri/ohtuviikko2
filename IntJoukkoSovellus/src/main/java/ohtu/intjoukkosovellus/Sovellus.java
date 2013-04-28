package ohtu.intjoukkosovellus;

import java.util.Scanner;

public class Sovellus {
    
    private IntJoukko A, B, C;
    Scanner lukija;

    public Sovellus(IntJoukko A, IntJoukko B, IntJoukko C) {
        this.A = A;
        this.B = B;
        this.C = C;
        lukija = new Scanner(System.in);
    }


    public IntJoukko mikaJoukko() {
        while (true) {
        String luettu = lukija.nextLine();
        luettu = luettu.toLowerCase();
        
            if (luettu.equals("a")) {
                return A;
            } else if (luettu.equals("b")) {
                return B;
            } else if (luettu.equals("c")) {
                return C;
            } else {
                System.out.println("Virheellinen joukko! " + luettu + " Yritä uudelleen!");
                          }
        }
    }

    public void lisaa() {
       
        Scanner lukija = new Scanner(System.in);
        System.out.println("Mihin joukkoon? ");
        IntJoukko joukko = mikaJoukko();
        System.out.println("Mikä luku lisätään? ");
        joukko.lisaa(lukija.nextInt());
        

    }

    public void yhdiste() {
       
        System.out.println("1. joukko? ");
        IntJoukko aJoukko = mikaJoukko();
        System.out.println("2. joukko? ");
        IntJoukko bJoukko = mikaJoukko();
        IntJoukko c = aJoukko.yhdiste(bJoukko);
        System.out.println("A yhdiste B = " + c.toString());
    }

    public void leikkaus() {
         
        System.out.println("1. joukko? ");
         IntJoukko aJoukko = mikaJoukko();
        System.out.println("2. joukko? ");
         IntJoukko bJoukko = mikaJoukko();
        IntJoukko c = aJoukko.leikkaus(bJoukko);
        System.out.println("A leikkaus B = " + c.toString());
        
    }

    public void erotus() {
        
        System.out.println("1. joukko? ");
        IntJoukko aJoukko = mikaJoukko();
        System.out.println("2. joukko? ");
        IntJoukko bJoukko = mikaJoukko();
        IntJoukko c = aJoukko.erotus(bJoukko);
        System.out.println("A erotus B = " + c.toString());
        
    }

    public void poista() {

        System.out.print("Mistä joukosta? ");
        IntJoukko joukko = mikaJoukko();
        System.out.print("Mikä luku poistetaan? ");
        joukko.poista(lukija.nextInt());
    }

    public void kuuluu() {
        System.out.print("Mihin joukkoon? ");
        IntJoukko joukko = mikaJoukko();
        System.out.print("Mikä luku? ");
        int kysLuku = lukija.nextInt();
        System.out.println(joukko.kuuluu(kysLuku) ? (kysLuku + " kuuluu joukkoon ") : (kysLuku + " ei kuulu joukkoon "));
    }
}
