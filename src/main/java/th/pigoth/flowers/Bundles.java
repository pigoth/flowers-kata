package th.pigoth.flowers;

import java.math.BigDecimal;
import java.util.Map;

public class Bundles {

    private final String flowerCode;
    private final Map<Integer, BigDecimal> values;

    public Bundles(String flowerCode, Map<Integer, BigDecimal> values) {
        this.flowerCode = flowerCode;
        this.values = values;
    }

    public Map<Integer, BigDecimal> all() {
        return values;
    }

    public String flowerCode() {
        return flowerCode;
    }
}
