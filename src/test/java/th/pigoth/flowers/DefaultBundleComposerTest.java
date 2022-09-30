package th.pigoth.flowers;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class DefaultBundleComposerTest {

    //    private final FlowerBundles tripleBundles = new FlowerBundles(
//            "L09",
//            List.of(new Bundle(3, valueOf(5.95)), new Bundle(5, valueOf(9.95)), new Bundle(9, valueOf(16.99)))
//    );

    @Test
    void should_sell_more_roses_bundle() {
        DefaultBundleComposer bundleComposer = new DefaultBundleComposer(new FlowerBundles(
                "R12",
                List.of(new Bundle(5, valueOf(6.99)), new Bundle(10, valueOf(12.99)))
        ));

        String totalAmount = bundleComposer.totalAmount(20);

        assertThat(totalAmount).isEqualTo("20 R12 $25.98 (2 x 10 $12.99)");
    }

    @Test
    void should_sell_different_type_of_bundles() {
        DefaultBundleComposer bundleComposer = new DefaultBundleComposer(new FlowerBundles(
                "R12",
                List.of(new Bundle(5, valueOf(6.99)), new Bundle(10, valueOf(12.99)))
        ));

        String totalAmount = bundleComposer.totalAmount(15);

        assertThat(totalAmount).isEqualTo("15 R12 $19.98 (1 x 10 $12.99 - 1 x 5 $6.99)");
    }

    @Test
    void should_sell_bundles_with_proportional_three_level() {
        DefaultBundleComposer bundleComposer = new DefaultBundleComposer(new FlowerBundles(
                "L09",
                List.of(new Bundle(3, valueOf(9.95)), new Bundle(6, valueOf(16.95)), new Bundle(9, valueOf(24.95)))
        ));
        String totalAmount = bundleComposer.totalAmount(15);

        assertThat(totalAmount).isEqualTo("15 L09 $41.90 (1 x 9 $24.95 - 1 x 6 $16.95)");
    }

}
