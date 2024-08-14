package com.loop614.analyticstribble.tracker.transfer;

public class TrackerFilterTransfer {

    public String domain;
    public String customer;
    public String date;

    public TrackerFilterTransfer(
        String domain,
        String customer,
        String date
    ) {
        this.domain = domain;
        this.customer = customer;
        this.date = date;
    }
}
