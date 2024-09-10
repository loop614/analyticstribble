package com.loop614.analyticstribble.tracker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import com.loop614.analyticstribble.swipe.SwipeService;
import com.loop614.analyticstribble.swipe.entity.Swipe;
import com.loop614.analyticstribble.swipe.transfer.SwipeInputTransfer;
import com.loop614.analyticstribble.tracker.entity.Tracker;
import com.loop614.analyticstribble.tracker.repository.TrackerRepository;
import com.loop614.analyticstribble.tracker.transfer.TrackerFilterTransfer;
import com.loop614.analyticstribble.tracker.transfer.TrackerInputTransfer;
import com.loop614.analyticstribble.tracker.transfer.TrackerTransfer;
import com.loop614.analyticstribble.tracker.transfer.VectorTransfer;

@Service
public class TrackerServiceImpl implements TrackerService {

    @Autowired
    ElasticsearchOperations operations;

    @Autowired
    TrackerRepository repository;

    @Autowired
    SwipeService swipeService;

    public TrackerServiceImpl(ElasticsearchOperations operations, TrackerRepository repository, SwipeService swipeService) {
        this.operations = operations;
        this.repository = repository;
        this.swipeService = swipeService;
    }

    @Override
    public List<Tracker> save(TrackerInputTransfer trackerInputTransfer) {
        Swipe swipe = this.swipeService.save(SwipeInputTransfer.builder()
                .domain(trackerInputTransfer.domain)
                .customer(trackerInputTransfer.customer)
                .dateNano(trackerInputTransfer.dateNano)
                .build()
        );

        operations.indexOps(Tracker.class).refresh();
        List<Tracker> docs = new ArrayList<>();

        for (VectorTransfer trail : trackerInputTransfer.trails) {
            docs.add(Tracker.builder()
                    .swipeId(swipe.getId())
                    .x(trail.x)
                    .y(trail.y)
                    .dt(trail.dt)
                    .build());
        }
        Iterable<Tracker> savedDocs = repository.<Tracker>saveAll(docs);
        ArrayList<Tracker> result = new ArrayList<>();
        savedDocs.forEach(result::add);

        return result;
    }

    @Override
    public TrackerTransfer getTrackers(TrackerFilterTransfer filterTracker) {
        Swipe swipe = this.swipeService.findByDomainAndCustomerAndDateNano(
                filterTracker.domain,
                filterTracker.customer,
                filterTracker.dateNano
        );
        List<Tracker> hits = this.repository.findBySwipeId(swipe.getId());
        TrackerTransfer res = new TrackerTransfer(swipe.getDomain(), swipe.getCustomer(), swipe.getDatenano());

        for (Tracker currentTracker : hits) {
            res.trails.add(
                    new VectorTransfer(
                            currentTracker.getX(),
                            currentTracker.getY(),
                            currentTracker.getDt()
                    )
            );
        }

        return res;
    }
}
