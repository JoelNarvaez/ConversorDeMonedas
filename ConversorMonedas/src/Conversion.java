public class Conversion {
    private String from; // moneda base
    private String to; // moneda a la que se quiere convertir
    private Double amount;
    private double result;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "La conversion de "+ this.amount +" "+ this.from + " a " + this.to + " = " + this.result;
    }
}
