package th.pigoth.flowers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.lang.String.format;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class ShopTest {

    private Shop shop;

    @BeforeEach
    void setUp() {
        shop = new Shop();
    }

    @Test
    void should_sell_more_roses_bundle() {
        String totalAmount = shop.totalAmount(20, 0, 0);

        assertThat(totalAmount).isEqualTo("20 R12 $25.98 (2 x 10 $12.99 - 0 x 5 $6.99)");
    }

    @Test
    void should_sell_different_type_of_bundles() {
        String totalAmount = shop.totalAmount(15, 0, 0);

        assertThat(totalAmount).isEqualTo("15 R12 $19.98 (1 x 10 $12.99 - 1 x 5 $6.99)");
    }
}
