package com.loop614.analyticstribble.tracker.transfer;

public class TrackerFilterTransfer {

    public String domain;
    public String customer;
    public String dateNano;

    public TrackerFilterTransfer(
        String domain,
        String customer,
        String dateNano
    ) {
        this.domain = domain;
        this.customer = customer;
        this.dateNano = dateNano;
    }
}
