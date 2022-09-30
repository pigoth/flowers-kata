package th.pigoth.flowers;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toUnmodifiableList;

public class Bundles {

    private final String flowerCode;
    private final List<Bundle> values;

    public Bundles(String flowerCode, List<Bundle> values) {
        this.flowerCode = flowerCode;
        this.values = values.stream()
                .sorted(comparing(Bundle::getSize).reversed())
                .collect(toUnmodifiableList());
    }

    public List<Bundle> all() {
        return values;
    }

    public String flowerCode() {
        return flowerCode;
    }
}
