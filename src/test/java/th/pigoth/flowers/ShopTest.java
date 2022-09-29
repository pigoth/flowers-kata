package th.pigoth.flowers;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ShopTest {

    @Test
    @Disabled
    void should_sell_single_roses_bundle() {
        Shop shop = new Shop();
        Integer nRoses = 10;
        Integer nLilies = 0;
        Integer nTulips = 0;

        BigDecimal totalAmount = shop.totalAmount(nRoses, nLilies, nTulips);

        assertThat(totalAmount).isEqualTo(BigDecimal.valueOf(12.99));
    }
}
