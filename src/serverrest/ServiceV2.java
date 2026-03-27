/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverrest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import serverrest.parsers.RequestV1;
import serverrest.parsers.RequestV2;
import serverrest.parsers.ResponseV2;

import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author delfo
 */
public class ServiceV2 {
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final HashSet<String> rosso = new HashSet<>() {{
        add("1"); add("3"); add("5"); add("7"); add("9"); add("12"); add("14"); add("16"); add("18");
        add("19"); add("21"); add("23"); add("25"); add("27"); add("30"); add("32"); add("34");
        add("36");
    }};

    private static final HashSet<String> nero = new HashSet<>() {{
        add("2"); add("4"); add("6"); add("8"); add("10"); add("11"); add("13"); add("15"); add("17");
        add("20"); add("22"); add("24"); add("26"); add("28"); add("29"); add("31"); add("33");
        add("35");
    }};

    /**
     * Esegue l'operazione matematica richiesta
     * 
     * @param 
     * @param 
     * @param 
     * @return 
     * @throws IllegalArgumentException se ...
     */
    public static float logicaDiCalcolo(RequestV2 request) throws IllegalArgumentException, NumberFormatException {

        // Controllo se i parametri passati sono validi
        if (!parametriValidi(request)) {
            throw new IllegalArgumentException("Dati inseriti non validi. Parametri richiesti: giocata, numero, importo. La giocata deve essere 'ROSSO' o 'NERO', il numero deve essere un intero compreso tra 0 e 36 e la puntata maggiore di 0.0");
        }

        int numero = Integer.parseInt(request.getNumero());
        if (numero == 0)
            return 0;

        if (request.getGiocata().equalsIgnoreCase("ROSSO") && rosso.contains(request.getNumero()))
            return request.getImporto() * 2;
        else if (request.getGiocata().equalsIgnoreCase("NERO") && nero.contains(request.getNumero()))
            return request.getImporto() * 2;

        return 0;
    }

    public static String handleResponse(RequestV2 request) throws IllegalArgumentException, NumberFormatException, JsonSyntaxException {
        float vittoria = logicaDiCalcolo(request);

        // Crea l'oggetto risposta
        int numero = Integer.parseInt(request.getNumero());
        ResponseV2 response = new ResponseV2(request.getGiocata(), numero, String.valueOf(vittoria > 0), request.getImporto(), vittoria);

        // GSON converte automaticamente l'oggetto Java in JSON
        return gson.toJson(response);
    }

    // Metodo di validazione dei parametri (da implementare)
    private static boolean parametriValidi(RequestV2 request)
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

        if (!giocata.equalsIgnoreCase("ROSSO") && !giocata.equalsIgnoreCase("NERO"))
            return false;

        if (request.getImporto() <= 0.0)
            return false;

        try {
            int numero = Integer.parseInt(numeroStr);
            return numero >= 0 && numero <= 36;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
