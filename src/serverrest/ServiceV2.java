/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverrest;

import serverrest.parsers.RequestV1;
import serverrest.parsers.RequestV2;

import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author delfo
 */
public class ServiceV2 {

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
    public static boolean logicaDiCalcolo(RequestV2 request) throws IllegalArgumentException {

        // Controllo se i parametri passati sono validi
        if (!parametriValidi(request)) {
            throw new IllegalArgumentException("Dati inseriti non validi. La giocata deve essere 'ROSSO' o 'NERO', il numero deve essere un intero compreso tra 0 e 36 e la puntata maggiore di 0.0");
        }

        Integer numero = Integer.parseInt(request.getNumero());
        if (numero == 0)
            return false;

        if (request.getGiocata().equalsIgnoreCase("ROSSO"))
            return rosso.contains(request.getNumero());
        else
            return nero.contains(request.getNumero());
    }

    // Metodo di validazione dei parametri (da implementare)
    private static boolean parametriValidi(RequestV2 request)
    {
        return !request.getGiocata().isEmpty() && (request.getGiocata().equalsIgnoreCase("ROSSO") || request.getGiocata().equalsIgnoreCase("NERO")) && !request.getNumero().isEmpty() && Integer.parseInt(request.getNumero()) <= 36 && request.getImporto() > 0.0;
    }
}
