package com.loop614.analyticstribble.swipe.transfer;

public class SwipeFilterTransfer {

    public String domain;
    public String customer;
    public String dateFrom;
    public String dateTo;

    public SwipeFilterTransfer(
        String domain,
        String customer,
        String dateFrom,
        String dateTo
    ) {
        this.domain = domain;
        this.customer = customer;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
