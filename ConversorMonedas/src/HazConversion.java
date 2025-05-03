public record HazConversion(boolean success, Query query, double result) {
    public record Query(String from, String to, double amount) {}

    @Override
    public String toString() {
        return "La conversion de "+ this.query.amount +" "+ this.query.from + " a " + this.query.to + " = " + this.result;
    }
}