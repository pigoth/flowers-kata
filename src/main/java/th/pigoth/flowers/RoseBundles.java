package th.pigoth.flowers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import static java.lang.String.format;
import static java.math.BigDecimal.*;
import static java.math.BigDecimal.valueOf;
import static java.util.stream.Collectors.joining;

public class RoseBundles implements FlowerBundles {
    public static final int L_ROSE_BUNDLE = 10;
    public static final int S_ROSE_BUNDLE = 5;
    private final Map<Integer, BigDecimal> rosesBundles = Map.of(
            L_ROSE_BUNDLE, valueOf(12.99),
            S_ROSE_BUNDLE, valueOf(6.99)
    );

    @Override
    public String totalAmount(Integer nFlowers) {

        ArrayList<BundleComposition> result = new ArrayList<>();

        rosesBundles.forEach((size, amount) -> {
            int nFreeFlowers = nFlowers - result.stream().mapToInt(BundleComposition::totalFlowers).sum();
            var bundleQuantity = nFreeFlowers / size;
            result.add(new BundleComposition(size, bundleQuantity, amount));
        });

        return formatResult(nFlowers, result);

    }

    private String formatResult(Integer nFlowers, ArrayList<BundleComposition> result) {
        BigDecimal totalAmount = result.stream()
                .map(BundleComposition::totalAmount)
                .reduce(ZERO, BigDecimal::add);

        String details = result.stream()
                .map(BundleComposition::toString)
                .collect(joining(" - ", "(", ")"));

        return format("%d R12 $%.2f %s", nFlowers, totalAmount, details);
    }

    class BundleComposition {

        private final Integer bundleSize;
        private final Integer bundleQuantity;
        private final BigDecimal bundleAmount;

        public BundleComposition(Integer bundleSize, Integer bundleQuantity, BigDecimal bundleAmount) {
            this.bundleSize = bundleSize;
            this.bundleQuantity = bundleQuantity;
            this.bundleAmount = bundleAmount;
        }

        public int totalFlowers() {
            return bundleSize * bundleQuantity;
        }

        public BigDecimal totalAmount() {
            return bundleAmount.multiply(valueOf(bundleQuantity));
        }

        @Override
        public String toString() {
            return format("%d x %d $%.2f", bundleQuantity, bundleSize, bundleAmount);
        }
    }
}
