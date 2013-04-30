package com.mycompany.webkauppa;

import com.mycompany.webkauppa.ohjaus.KomentoTehdas;
import com.mycompany.webkauppa.ohjaus.OstoksenPoistoKorista;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PoistaOstoskoristaServlet extends WebKauppaServlet {
             
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        KomentoTehdas komento = new KomentoTehdas();        
        long tuoteId = Long.parseLong( request.getParameter("tuoteId") );
        
        komento.OstoksenPoistoKorista(haeSessionOstoskori(request),  tuoteId ).suorita();          
        
        
        naytaSivu("/MaksaOstokset", request, response);
    }
}
