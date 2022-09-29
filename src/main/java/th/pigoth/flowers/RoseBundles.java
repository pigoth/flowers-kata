package th.pigoth.flowers;

import java.math.BigDecimal;
import java.util.Map;

import static java.lang.String.format;
import static java.math.BigDecimal.valueOf;

public class RoseBundles implements FlowerBundles {
    public static final int L_ROSE_BUNDLE = 10;
    public static final int S_ROSE_BUNDLE = 5;
    private Map<Integer, BigDecimal> rosesBundles = Map.of(
            S_ROSE_BUNDLE, valueOf(6.99),
            L_ROSE_BUNDLE, valueOf(12.99)
    );

    @Override
    public String totalAmount(Integer nFlowers) {
        int largeRosesBundleCount = nFlowers / L_ROSE_BUNDLE;
        int smallRosesBundleCount = (nFlowers - largeRosesBundleCount * L_ROSE_BUNDLE) / S_ROSE_BUNDLE;
        BigDecimal amountLBundles = rosesBundles.get(L_ROSE_BUNDLE).multiply(valueOf(largeRosesBundleCount));
        BigDecimal amountSBundles = rosesBundles.get(S_ROSE_BUNDLE).multiply(valueOf(smallRosesBundleCount));

        BigDecimal totalAmount = amountLBundles.add(amountSBundles);
        return format(
                "%d R12 $%.2f (%d x 10 $%.2f - %d x 5 $%.2f)",
                nFlowers, totalAmount,
                largeRosesBundleCount, rosesBundles.get(L_ROSE_BUNDLE),
                smallRosesBundleCount, rosesBundles.get(S_ROSE_BUNDLE)
        );
    }
}
