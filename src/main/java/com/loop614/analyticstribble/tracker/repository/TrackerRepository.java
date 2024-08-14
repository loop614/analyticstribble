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
                    {"match": {"domain": "?0"}},
                    {"match": {"customer": "?1"}},
                    {"match": {"date": "?2"}}]}}"
                ]
            }
        }
        """)
    List<Tracker> findByDomainAndCustomerAndDate(String domain, String customer, String date);
}
