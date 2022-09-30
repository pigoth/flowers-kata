package th.pigoth.flowers;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toUnmodifiableList;

public class FlowerBundles {

    private final String flowerCode;
    private final List<Bundle> bundles;

    public FlowerBundles(String flowerCode, List<Bundle> bundles) {
        this.flowerCode = flowerCode;
        this.bundles = bundles.stream()
                .sorted(comparing(Bundle::getSize).reversed())
                .collect(toUnmodifiableList());
    }

    public List<Bundle> all() {
        return bundles;
    }

    public String flowerCode() {
        return flowerCode;
    }
}
