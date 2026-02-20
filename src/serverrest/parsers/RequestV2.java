package serverrest.parsers;

public class RequestV2 extends RequestV1 {
    // Costruttore vuoto necessario per GSON
    public RequestV2() {
        super();
    }

    public RequestV2(String giocata, String numero, float importo) {
        super(giocata, numero);
        this.importo = importo;
    }

    private float importo;

    public float getImporto() {
        return importo;
    }

    public void setImporto(float importo) {
        this.importo = importo;
    }
}
