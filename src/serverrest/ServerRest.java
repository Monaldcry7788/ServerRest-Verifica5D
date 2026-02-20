/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package serverrest;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import serverrest.handlers.v1.GetHandlerV1;
import serverrest.handlers.v1.PostHandlerV1;
import serverrest.handlers.v2.GetHandlerV2;
import serverrest.handlers.v2.PostHandlerV2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;



/**
 * Server REST per la calcolatrice
 * 
 * @author delfo
 */
public class ServerRest {

    /**
     * Avvia il server REST sulla porta specificata
     * 
     * @param porta la porta su cui avviare il server
     */
    public static void avviaServer(int porta) {
        try {
            // Crea il server sulla porta specificata
            HttpServer server = HttpServer.create(new InetSocketAddress(porta), 0);
            
            // Registra gli handler per gli endpoint
            server.createContext("/api/roulette/paridispari/get", new GetHandlerV1());
            server.createContext("/api/roulette/paridispari/post", new PostHandlerV1());

            server.createContext("/api/v2/roulette/colori/get", new GetHandlerV2());
            server.createContext("/api/v2/roulette/colori/post", new PostHandlerV2());
           
            
            // Endpoint di benvenuto
            server.createContext("/", ServerRest::gestisciBenvenuto);
            
            // Avvia il server
            server.setExecutor(null); // Usa il default executor
            server.start();
            
            // Messaggi di conferma
            System.out.println("==============================================");
            System.out.println("  Server REST con GSON avviato!");
            System.out.println("==============================================");
            System.out.println("Porta: " + porta);
            System.out.println();
            System.out.println("Endpoint disponibili:");
            System.out.println("  - POSTv1: http://localhost:" + porta + "/api/roulette/paridispari/post");
            System.out.println("  - GETv1:  http://localhost:" + porta + "/api/roulette/paridispari/get");
            System.out.println("  - POSTv2: http://localhost:" + porta + "/api/v2/roulette/colori/post");
            System.out.println("  - GETv2:  http://localhost:" + porta + "/api/v2/roulette/colori/get");
            System.out.println("  - Info: http://localhost:" + porta + "/");
            System.out.println();
            System.out.println();
            System.out.println("Premi Ctrl+C per fermare il server");
            System.out.println("==============================================");
            
        } catch (IOException e) {
            System.err.println("Errore nell'avvio del server: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Gestisce l'endpoint di benvenuto che fornisce informazioni sull'API
     * 
     * @param exchange l'oggetto HttpExchange per gestire la richiesta/risposta
     * @throws IOException in caso di errori durante la comunicazione
     */
    private static void gestisciBenvenuto(HttpExchange exchange) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        Map info = new HashMap<>();
        info.put("messaggio", "Benvenuto alla Roulette REST API");
        info.put("versione", "2.0.0");
        info.put("tecnologia", "Java + GSON");
        
        Map endpoints = new HashMap<>();
        endpoints.put("POSTv1", "/api/roulette/paridispari/post");
        endpoints.put("GETv1", "/api/roulette/paridispari/get?giocata=PARI&numero=5");
        endpoints.put("POSTv2", "/api/v2/roulette/colori/post");
        endpoints.put("GETv2", "/api/v2/roulette/colori/get?giocata=ROSSO&numero=5&importo=10.0");
        info.put("endpoints", endpoints);
        
        Map parametri = new HashMap<>();
        parametri.put("V1", "parametri per versione API 1.0.0");
        parametri.put("giocata", "PARI o DISPARI (versione v1) / ROSSO o NERO (versione v2)");
        parametri.put("numero", "Un numero da 0 a 36");
        parametri.put("V2", "parametri per versione API 2.0.0");
        parametri.put("importo", "Importo della giocata (float)");

        info.put("parametri richiesti", parametri);
        
        String jsonRisposta = gson.toJson(info);
        
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        byte[] bytes = jsonRisposta.getBytes();
        exchange.sendResponseHeaders(200, bytes.length);
        exchange.getResponseBody().write(bytes);
        exchange.getResponseBody().close();
    }
}