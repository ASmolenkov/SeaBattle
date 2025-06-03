public enum ShotResult {
    HIT("💥"), MISS("🌊"), ALREADY_SHOT("🚫");

    private final String symbol;

     ShotResult(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}

