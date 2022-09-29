package th.pigoth.flowers;

import java.math.BigDecimal;
import java.util.Map;

import static java.math.BigDecimal.valueOf;

public class Shop {
    public static final int L_ROSE_BUNDLE = 10;
    public static final int S_ROSE_BUNDLE = 5;
    private Map<Integer, BigDecimal> rosesBundles = Map.of(
            S_ROSE_BUNDLE, valueOf(6.99),
            L_ROSE_BUNDLE, valueOf(12.99)
    );

    public BigDecimal totalAmount(Integer nRoses, Integer nLilies, Integer nTulips) {
        int largeRosesBundleCount = nRoses / L_ROSE_BUNDLE;
        return rosesBundles.get(L_ROSE_BUNDLE).multiply(valueOf(largeRosesBundleCount));
    }
}
