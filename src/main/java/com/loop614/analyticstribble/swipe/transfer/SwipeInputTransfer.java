package com.loop614.analyticstribble.swipe.transfer;

import lombok.Builder;

@Builder
public class SwipeInputTransfer {

    public String domain;
    public String customer;
    public String dateNano;

    public SwipeInputTransfer(
        String domain,
        String customer,
        String dateNano
    ) {
        this.domain = domain;
        this.customer = customer;
        this.dateNano = dateNano;
    }
}
