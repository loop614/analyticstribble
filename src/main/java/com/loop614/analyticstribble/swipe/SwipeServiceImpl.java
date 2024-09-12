package com.loop614.analyticstribble.swipe;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loop614.analyticstribble.swipe.entity.Swipe;
import com.loop614.analyticstribble.swipe.repository.SwipeRepository;
import com.loop614.analyticstribble.swipe.transfer.SwipeFilterTransfer;
import com.loop614.analyticstribble.swipe.transfer.SwipeInputTransfer;

@Service
public class SwipeServiceImpl implements SwipeService {

    @Autowired
    SwipeRepository repository;

    public SwipeServiceImpl(SwipeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Swipe> getSwipes(SwipeFilterTransfer swipeFilter) {
        return this.repository.findByDomainAndCustomerAndDateBetween(
            swipeFilter.domain,
            swipeFilter.customer,
            swipeFilter.dateFrom,
            swipeFilter.dateTo
        );
    }

    @Override
    public Swipe save(SwipeInputTransfer swipeInputTransfer) {
        Swipe doc = Swipe.builder()
                .domain(swipeInputTransfer.domain)
                .customer(swipeInputTransfer.customer)
                .datenano(swipeInputTransfer.date)
                .build();

        return repository.<Swipe>save(doc);
    }

    @Override
    public Optional<Swipe> findBySwipeId(String swipeId) {
        return this.repository.findById(swipeId);
    }
}
