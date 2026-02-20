/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverrest.parsers;

/**
 *
 * @author delfo
 */
public class RequestV1 {
    // Costruttore vuoto necessario per GSON
    public RequestV1() {
    }

    public RequestV1(String giocata, String numero) {
        this.giocata = giocata;
        this.numero = numero;
    }

    private String giocata;
    private String numero;

    public String getGiocata() {
        return giocata;
    }

    public void setGiocata(String giocata) {
        this.giocata = giocata;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}