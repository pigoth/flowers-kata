package th.pigoth.flowers;

import java.math.BigDecimal;

public class Bundle {
    private final Integer size;
    private final BigDecimal amount;

    public Bundle(Integer size, BigDecimal amount) {
        this.size = size;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Integer getSize() {
        return size;
    }
}
