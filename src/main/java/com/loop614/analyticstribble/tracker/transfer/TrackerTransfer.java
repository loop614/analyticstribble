package com.loop614.analyticstribble.tracker.transfer;

import java.util.ArrayList;
import java.util.List;

public class TrackerTransfer {

    public String domain;
    public String customer;
    public String dateNano;
    public List<VectorTransfer> trails;

    public TrackerTransfer() {
        this.trails = new ArrayList<>();
    }

    public TrackerTransfer(String domain, String customer, String dateNano) {
        this.domain = domain;
        this.customer = customer;
        this.dateNano = dateNano;
        this.trails = new ArrayList<>();
    }
}
