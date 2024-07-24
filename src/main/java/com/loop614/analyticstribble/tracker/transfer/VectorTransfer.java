package com.loop614.analyticstribble.tracker.transfer;

import java.math.BigDecimal;

public class VectorTransfer {

    public BigDecimal x;
    public BigDecimal y;
    public BigDecimal dt;

    public VectorTransfer(BigDecimal x, BigDecimal y, BigDecimal dt) {
        this.x = x;
        this.y = y;
        this.dt = dt;
    }
}
