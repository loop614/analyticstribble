package com.loop614.analyticstribble.swipe.transfer;

public class SwipeFilterTransfer {

    public String domain;
    public String customer;
    public String dateNanoFrom;
    public String dateNanoTo;

    public SwipeFilterTransfer(
        String domain,
        String customer,
        String dateNanoFrom,
        String dateNanoTo
    ) {
        this.domain = domain;
        this.customer = customer;
        this.dateNanoFrom = dateNanoFrom;
        this.dateNanoTo = dateNanoTo;
    }
}
