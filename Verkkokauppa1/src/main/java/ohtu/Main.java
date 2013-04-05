package ohtu;

import java.util.Scanner;
import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Kirjanpito;
import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.Varasto;
import ohtu.verkkokauppa.Viitegeneraattori;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");
        Kirjanpito kirjanpito = (Kirjanpito) ctx.getBean(Kirjanpito.class);
        Kauppa kauppa =(Kauppa) ctx.getBean(Kauppa.class);
//        Kirjanpito kirjanpito = new Kirjanpito();
//        Varasto varasto = new Varasto(kirjanpito);
//        Pankki pankki = new Pankki(kirjanpito);
//        Viitegeneraattori gene = new Viitegeneraattori();
//        
        


        // kauppa hoitaa yhden asiakkaan kerrallaan seuraavaan tapaan:
        Scanner scn = new Scanner(System.in); 
        kauppa.aloitaAsiointi();
        while (true) {
            System.out.println("Anna komento");
            int komento = scn.nextInt();
            if (komento == 0) {
                System.exit(0);
            } else if (komento == 1) {
                System.out.println("Anna Id");
                kauppa.lisaaKoriin(scn.nextInt());
            } else if (komento == 2) {
                System.out.println("Anna Id");
                kauppa.poistaKorista(scn.nextInt());
            } else if (komento == 3) {
                kirjanpito.getTapahtumat();
            }
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    System.out.println("uli");
                }
            }
        }
    }
}
