package com.loop614.analyticstribble.tracker.transfer;

import java.util.List;

public class TrackerTransfer {

    public String domain;
    public String customer;
    public List<VectorTransfer> trails;

    public TrackerTransfer(String domain, String customer) {
        this.domain = domain;
        this.customer = customer;
    }
}
