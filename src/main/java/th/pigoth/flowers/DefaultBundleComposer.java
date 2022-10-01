package th.pigoth.flowers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toUnmodifiableList;
import static java.util.stream.Stream.concat;
import static java.util.stream.Stream.of;

public class DefaultBundleComposer implements BundleComposer {

    private final FlowerBundles flowerBundles;

    public DefaultBundleComposer(FlowerBundles flowerBundles) {
        this.flowerBundles = flowerBundles;
    }

    @Override
    public String totalAmount(Integer totalFlowers) {
        int[] bundleCount = initializedBundleCount(totalFlowers);
        HashMap<Integer, List<BundleComposition>> result = initializedResults(totalFlowers);

        for (int nFlowers = 1; nFlowers <= totalFlowers; nFlowers++) {
             for (Bundle bundle : flowerBundles.all()) {
                boolean canUseBundle = bundle.getSize() <= nFlowers;
                if (canUseBundle) {
                    int flowerOutOfBundle = nFlowers - bundle.getSize();
                    int flowerOutOfBundlePreviusComposition = bundleCount[flowerOutOfBundle];
                    if (flowerOutOfBundlePreviusComposition != MAX_VALUE && flowerOutOfBundlePreviusComposition + 1 < bundleCount[nFlowers]) {
                        bundleCount[nFlowers] = flowerOutOfBundlePreviusComposition + 1;
                        result.put(nFlowers, bundleCompositionsFor(bundle, result.get(flowerOutOfBundle))) ;
                    }
                }
            }
        }
        return formatResult(totalFlowers, result.get(totalFlowers));
    }

    private List<BundleComposition> bundleCompositionsFor(Bundle bundle, List<BundleComposition> flowerOutOfCurrentBundleComposition) {
        if (flowerOutOfCurrentBundleComposition.stream().anyMatch(composition -> composition.bundleSize == bundle.getSize())) {
            return flowerOutOfCurrentBundleComposition.stream()
                    .map(composition -> composition.bundleSize == bundle.getSize() ? new BundleComposition(bundle, composition.bundleQuantity + 1) : composition)
                    .collect(toUnmodifiableList());
        } else {
            return concat(flowerOutOfCurrentBundleComposition.stream(), of(new BundleComposition(bundle.getSize(), 1, bundle.getAmount())))
                    .collect(toUnmodifiableList());
        }
    }

    private static int[] initializedBundleCount(Integer totalFlowers) {
        int[] table = new int[totalFlowers + 1];
        table[0] = 0;
        for (int i = 1; i <= totalFlowers; i++)
            table[i] = MAX_VALUE;
        return table;
    }

    private static HashMap<Integer, List<BundleComposition>> initializedResults(Integer totalFlowers) {
        var result = new HashMap<Integer, List<BundleComposition>>();
        result.put(0, new ArrayList<>());
        for (int i = 1; i < totalFlowers + 1; i++) result.put(i, null);
        return result;
    }

    private String formatResult(Integer nFlowers, List<BundleComposition> result) {
        BigDecimal totalAmount = result.stream()
                .map(BundleComposition::totalAmount)
                .reduce(ZERO, BigDecimal::add);

        String details = result.stream()
                .filter(bundleComposition -> bundleComposition.bundleQuantity > 0)
                .sorted((f1, f2) -> Integer.compare(f2.bundleSize, f1.bundleSize))
                .map(BundleComposition::toString)
                .collect(joining(" - ", "(", ")"));

        return format("%d %s $%.2f %s", nFlowers, flowerBundles.flowerCode(), totalAmount, details);
    }

    private class BundleComposition {

        private final Integer bundleSize;
        private final Integer bundleQuantity;
        private final BigDecimal bundleAmount;

        public BundleComposition(Integer bundleSize, Integer bundleQuantity, BigDecimal bundleAmount) {
            this.bundleSize = bundleSize;
            this.bundleQuantity = bundleQuantity;
            this.bundleAmount = bundleAmount;
        }

        public BundleComposition(Bundle bundle, Integer bundleQuantity) {
            this.bundleSize = bundle.getSize();
            this.bundleQuantity = bundleQuantity;
            this.bundleAmount = bundle.getAmount();
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

