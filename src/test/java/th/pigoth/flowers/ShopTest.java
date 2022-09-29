package th.pigoth.flowers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ShopTest {

    private Shop shop;

    @BeforeEach
    void setUp() {
        shop = new Shop();
    }

    @Test
    void should_sell_single_roses_bundle() {
        BigDecimal totalAmount = shop.totalAmount(10, 0, 0);

        assertThat(totalAmount).isEqualTo(valueOf(12.99));
    }

    @Test
    void should_sell_more_roses_bundle() {
        BigDecimal totalAmount = shop.totalAmount(20, 0, 0);

        assertThat(totalAmount).isEqualTo(valueOf(12.99 * 2));
    }
}
