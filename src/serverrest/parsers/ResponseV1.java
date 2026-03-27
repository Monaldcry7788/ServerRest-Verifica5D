/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverrest.parsers;

/**
 *
 * @author delfo
 */
public class ResponseV1 {
    public ResponseV1(String giocata, int numero, String vittoria) {
        this.giocata = giocata;
        this.numero = numero;
        this.vittoria = vittoria;
    }

    // Costruttore vuoto necessario per GSON
    public ResponseV1() {
    }

    private String giocata;
    private int numero;
    private String vittoria;

    public String getGiocata() {
        return giocata;
    }

    public void setGiocata(String giocata) {
        this.giocata = giocata;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String isVittoria() {
        return vittoria;
    }

    public void setVittoria(String vittoria) {
        this.vittoria = vittoria;
    }
}