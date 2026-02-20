/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverrest;

/**
 *
 * @author delfo
 */
public class Service {
    
    /**
     * Esegue l'operazione matematica richiesta
     * 
     * @param 
     * @param 
     * @param 
     * @return 
     * @throws IllegalArgumentException se ...
     */
    public static boolean logicaDiCalcolo(Request request) throws IllegalArgumentException {
        
        // Controllo se i parametri passati sono validi
        if (!parametriValidi(request)) {
            throw new IllegalArgumentException("Dati inseriti non validi. La giocata deve essere 'PARI' o 'DISPARI' e il numero deve essere un intero compreso tra 0 e 36.");
        }

        Integer numero = Integer.parseInt(request.getNumero());
        return (request.getGiocata().equalsIgnoreCase("PARI") && numero % 2 == 0) || (request.getGiocata().equalsIgnoreCase("DISPARI") && numero % 2 != 0);
    }

    // Metodo di validazione dei parametri (da implementare)
    private static boolean parametriValidi(Request request)
    {
        return !request.getGiocata().isEmpty() && (request.getGiocata().equalsIgnoreCase("PARI") || request.getGiocata().equalsIgnoreCase("DISPARI") && !request.getNumero().isEmpty() && Integer.parseInt(request.getNumero()) <= 36);
    }
}
