package com.loop614.analyticstribble.tracker.repository;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.loop614.analyticstribble.tracker.entity.Tracker;

public interface TrackerRepository extends ElasticsearchRepository<Tracker, String> {

    @Query("""
        {
            "bool": {
                "must": [
                    {"match": {"swipeId": "?0"}}
                ]
            }
        }
        """)
    List<Tracker> findBySwipeId(String swipeId);
}
