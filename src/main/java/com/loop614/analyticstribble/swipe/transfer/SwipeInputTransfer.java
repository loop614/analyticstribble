package com.loop614.analyticstribble.swipe.transfer;

import lombok.Builder;

@Builder
public class SwipeInputTransfer {

    public String domain;
    public String customer;
    public String date;

    public SwipeInputTransfer(
        String domain,
        String customer,
        String date
    ) {
        this.domain = domain;
        this.customer = customer;
        this.date = date;
    }
}
