/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package serverrest;

import java.io.IOException;
import java.util.Arrays;

/**
 * Entry point dell'applicazione Calcolatrice REST
 * 
 * @author delfo
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int porta = 0;
        if (args.length > 0) {
            try {
                System.out.println("Porta specificata da argomento: " + args[0]);
                porta = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Porta non valida, uso porta default 8080");
            }
        } else
        {
            try {
                System.out.println("Nessuna porta specificata, inserisci la porta su cui avviare il server (default 8080): ");
                porta = Integer.parseInt(System.console().readLine());
            } catch (NumberFormatException e) {
                System.err.println("Porta non valida, uso porta default 8080");
                porta = 8080;
            }
        }
        
        // Avvia il server REST
        ServerRest.avviaServer(porta);
    }
}
