package com.loop614.analyticstribble.tracker.transfer;

import java.util.ArrayList;
import java.util.List;

public class TrackerTransfer {

    public String domain;
    public String customer;
    public String date;
    public List<VectorTransfer> trails;

    public TrackerTransfer() {
        this.trails = new ArrayList<>();
    }

    public TrackerTransfer(String domain, String customer, String date) {
        this.domain = domain;
        this.customer = customer;
        this.date = date;
        this.trails = new ArrayList<>();
    }
}
