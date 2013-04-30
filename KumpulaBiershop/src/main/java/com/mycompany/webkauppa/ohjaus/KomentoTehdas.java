/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webkauppa.ohjaus;

import com.mycompany.webkauppa.sovelluslogiikka.Ostoskori;
import com.mycompany.webkauppa.ulkoiset_rajapinnat.PankkiFasaadi;

/**
 *
 * @author Lauri
 */
public class KomentoTehdas {
    
    public KomentoTehdas() {
        
    }
     public Komento OstoksenLisaysKoriin(Ostoskori ostoskori, long tuoteId) {
         return new OstoksenLisaysKoriin(ostoskori, tuoteId);
     }
     
     public Komento OstoksenPoistoKorista (Ostoskori ostoskori, long tuoteId) {
         return new OstoksenPoistoKorista(ostoskori,tuoteId);
     }
     public Komento OstoksenSuoritus(String nimi, String osoite, String luottokorttinumero, Ostoskori kori) {
         return new OstoksenSuoritus(nimi, osoite, luottokorttinumero, kori);
     }
     public Komento OstoksenSuoritus(String nimi, String osoite, String luottokorttinumero, Ostoskori kori, PankkiFasaadi pankki) {
       OstoksenSuoritus ostos = new OstoksenSuoritus(nimi, osoite, luottokorttinumero, kori);
       ostos.setPankki(pankki);
       return ostos;
     }
}
