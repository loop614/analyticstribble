package com.loop614.analyticstribble.tracker.transfer;

import java.util.ArrayList;
import java.util.List;

public class TrackerTransfer {

    public String domain;
    public String customer;
    public List<VectorTransfer> trails;

    public TrackerTransfer() {
        this.trails = new ArrayList<>();
    }

    public TrackerTransfer(String domain, String customer) {
        this.domain = domain;
        this.customer = customer;
        this.trails = new ArrayList<>();
    }
}
