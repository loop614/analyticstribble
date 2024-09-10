package com.loop614.analyticstribble.swipe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import com.loop614.analyticstribble.swipe.entity.Swipe;
import com.loop614.analyticstribble.swipe.repository.SwipeRepository;
import com.loop614.analyticstribble.swipe.transfer.SwipeFilterTransfer;
import com.loop614.analyticstribble.swipe.transfer.SwipeInputTransfer;

@Service
public class SwipeServiceImpl implements SwipeService {

    @Autowired
    ElasticsearchOperations operations;

    @Autowired
    SwipeRepository repository;

    public SwipeServiceImpl(ElasticsearchOperations operations, SwipeRepository repository) {
        this.operations = operations;
        this.repository = repository;
    }

    @Override
    public List<Swipe> getSwipes(SwipeFilterTransfer swipeFilter) {
        return this.repository.findByDomainAndCustomerAndDateNanoBetween(
            swipeFilter.domain,
            swipeFilter.customer,
            swipeFilter.dateNanoFrom,
            swipeFilter.dateNanoTo
        );
    }

    @Override
    public Swipe save(SwipeInputTransfer swipeInputTransfer) {
        operations.indexOps(Swipe.class).refresh();
        Swipe doc = Swipe.builder()
                .domain(swipeInputTransfer.domain)
                .customer(swipeInputTransfer.customer)
                .datenano(swipeInputTransfer.dateNano)
                .build();

        return repository.<Swipe>save(doc);
    }

    @Override
    public Swipe findByDomainAndCustomerAndDateNano(String domain, String customer, String dateNano)
    {
        return this.repository.findByDomainAndCustomerAndDateNano(domain, customer, dateNano);
    }
}
