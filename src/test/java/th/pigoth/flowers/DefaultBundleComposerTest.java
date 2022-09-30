package th.pigoth.flowers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class DefaultBundleComposerTest {

    private BundleComposer roseBundles;

    @BeforeEach
    void setUp() {
        FlowerBundles flowerBundles = new FlowerBundles(
                "R12",
                List.of(new Bundle(5, valueOf(6.99)), new Bundle(10, valueOf(12.99)))
        );
        roseBundles = new DefaultBundleComposer(flowerBundles);
    }

    @Test
    void should_sell_more_roses_bundle() {
        String totalAmount = roseBundles.totalAmount(20);

        assertThat(totalAmount).isEqualTo("20 R12 $25.98 (2 x 10 $12.99 - 0 x 5 $6.99)");
    }

    @Test
    void should_sell_different_type_of_bundles() {
        String totalAmount = roseBundles.totalAmount(15);

        assertThat(totalAmount).isEqualTo("15 R12 $19.98 (1 x 10 $12.99 - 1 x 5 $6.99)");
    }
}
