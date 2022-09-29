package th.pigoth.flowers;

import java.math.BigDecimal;
import java.util.Map;

public class Shop {
    private Map<Integer, BigDecimal> rosesBundles = Map.of(
            5, BigDecimal.valueOf(6.99),
            10, BigDecimal.valueOf(12.99)
    );


    public BigDecimal totalAmount(Integer nRoses, Integer nLilies, Integer nTulips) {
        return rosesBundles.get(nRoses);
    }
}
