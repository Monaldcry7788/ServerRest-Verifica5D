package serverrest.parsers;

public class ResponseV2 extends ResponseV1 {
    // Costruttore vuoto necessario per GSON
    public ResponseV2() {
        super();
    }

    public ResponseV2(String giocata, String numero, boolean vittoria, float importogiocato, float importoriscosso) {
        super(giocata, numero, vittoria);
        this.importogiocato = importogiocato;
        this.importoriscosso = importoriscosso;
    }

    private float importogiocato;
    private float importoriscosso;

    public float getImportogiocato() {
        return importogiocato;
    }

    public void setImportogiocato(float importogiocato) {
        this.importogiocato = importogiocato;
    }

    public float getImportoriscosso() {
        return importoriscosso;
    }

    public void setImportoriscosso(float importoriscosso) {
        this.importoriscosso = importoriscosso;
    }
}