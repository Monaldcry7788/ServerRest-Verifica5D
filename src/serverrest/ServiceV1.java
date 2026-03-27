/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverrest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.Request;
import serverrest.parsers.RequestV1;
import serverrest.parsers.ResponseV1;

/**
 *
 * @author delfo
 */
public class ServiceV1 {
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    /**
     * Esegue l'operazione richiesta dal client, ovvero verifica se la giocata è vincente o meno in base al numero estratto.
     *
     * @param request la richiesta del client contenente la giocata e il numero
     * @return true se la giocata è vincente, false altrimenti
     * @throws IllegalArgumentException se i parametri della richiesta non sono validi (giocata diversa da "PARI" o "DISPARI", numero non compreso tra 0 e 36, o parametri null/empty)
     */
    public static boolean logicaDiCalcolo(RequestV1 request) throws IllegalArgumentException {

        // Controllo se i parametri passati sono validi
        if (!parametriValidi(request)) {
            throw new IllegalArgumentException("Dati inseriti non validi. La giocata deve essere 'PARI' o 'DISPARI' e il numero deve essere un intero compreso tra 0 e 36.");
        }

        int numero = Integer.parseInt(request.getNumero());
        if (numero == 0)
            return false;
        return (request.getGiocata().equalsIgnoreCase("PARI") && numero % 2 == 0) || (request.getGiocata().equalsIgnoreCase("DISPARI") && numero % 2 != 0);
    }

    public static String handleResponse(RequestV1 request) throws IllegalArgumentException, JsonSyntaxException, NumberFormatException {
        String vittoria = String.valueOf(logicaDiCalcolo(request));

        // Crea l'oggetto risposta
        int numero = Integer.parseInt(request.getNumero());
        ResponseV1 response = new ResponseV1(request.getGiocata(), numero, vittoria);

        // GSON converte automaticamente l'oggetto Java in JSON
        return gson.toJson(response);
    }

    private static boolean parametriValidi(RequestV1 request)
    {
        if (request == null)
            return false;

        String giocata = request.getGiocata();
        String numeroStr = request.getNumero();

        if (giocata == null || giocata.trim().isEmpty())
            return false;
        if (numeroStr == null || numeroStr.trim().isEmpty())
            return false;

        giocata = giocata.trim();
        numeroStr = numeroStr.trim();

        if (!giocata.equalsIgnoreCase("PARI") && !giocata.equalsIgnoreCase("DISPARI"))
            return false;

        try {
            int numero = Integer.parseInt(numeroStr);
            return numero >= 0 && numero <= 36;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}