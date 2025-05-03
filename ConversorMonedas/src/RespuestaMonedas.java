import java.util.Map;

public class RespuestaMonedas {
    private boolean success;
    private Map<String, String> currencies;

    public boolean isSuccess() {
        return success;
    }

    public Map<String, String> getCurrencies() {
        return currencies;
    }


}
