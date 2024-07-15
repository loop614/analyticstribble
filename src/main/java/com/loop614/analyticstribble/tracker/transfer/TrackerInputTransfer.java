package com.loop614.analyticstribble.tracker.transfer;

import java.math.BigDecimal;
import java.util.List;

class Vector {

    public BigDecimal x;
    public BigDecimal y;
}

public class TrackerInputTransfer {

    public String domain;
    public String customer;
    public List<Vector> trails;
}
