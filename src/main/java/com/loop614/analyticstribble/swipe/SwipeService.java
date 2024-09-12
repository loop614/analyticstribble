package com.loop614.analyticstribble.swipe;

import java.util.List;
import java.util.Optional;

import com.loop614.analyticstribble.swipe.entity.Swipe;
import com.loop614.analyticstribble.swipe.transfer.SwipeFilterTransfer;
import com.loop614.analyticstribble.swipe.transfer.SwipeInputTransfer;

public interface SwipeService {

    public Swipe save(SwipeInputTransfer swipeInputTransfer);

    public List<Swipe> getSwipes(SwipeFilterTransfer swipeFilter);

    public Optional<Swipe> findBySwipeId(String swipeId);
}
