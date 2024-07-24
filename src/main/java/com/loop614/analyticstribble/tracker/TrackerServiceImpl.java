package com.loop614.analyticstribble.tracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

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

    public TrackerServiceImpl(ElasticsearchOperations operations, TrackerRepository repository) {
        this.operations = operations;
        this.repository = repository;
    }

    @Override
    public ArrayList<Tracker> save(TrackerInputTransfer trackerInputTransfer) {
        operations.indexOps(Tracker.class).refresh();
        List<Tracker> docs = new ArrayList<>();

        for (VectorTransfer trail : trackerInputTransfer.trails) {
            docs.add(Tracker.builder()
                    .domain(trackerInputTransfer.domain)
                    .customer(trackerInputTransfer.customer)
                    .date(LocalDate.now())
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
        Criteria criteria = new Criteria("domain").is(filterTracker.domain).and("customer").is(filterTracker.customer).and("date").is(filterTracker.date);
        Query query = new CriteriaQuery(criteria);
        SearchHits<Tracker> hits = this.operations.search(query, Tracker.class);
        TrackerTransfer res = new TrackerTransfer(filterTracker.domain, filterTracker.customer);
        for (SearchHit<Tracker> hit : hits) {
            res.trails.add(
                    new VectorTransfer(
                            hit.getContent().getX(),
                            hit.getContent().getY(),
                            hit.getContent().getDt()
                    )
            );
        }

        return res;
    }
}
