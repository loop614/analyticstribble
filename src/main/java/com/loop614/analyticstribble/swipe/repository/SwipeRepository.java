package com.loop614.analyticstribble.swipe.repository;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.loop614.analyticstribble.swipe.entity.Swipe;

public interface SwipeRepository extends ElasticsearchRepository<Swipe, String> {
    @Query("""
        {
            "query": {
                "bool": {
                    "must": [
                        {"match": {"domain": "?0"}},
                        {"match": {"customer": "?1"}},
                        {"range": {
                           "datenano": {
                                "gte": "?2",
                                "lte": "?3"
                            }
                        }
                    ]
                }
            }
        }
        """)
    List<Swipe> findByDomainAndCustomerAndDateNanoBetween(String domain, String customer, String dateNaneFrom, String dateNanoTo);

    @Query("""
        {
            "bool": {
                "must": [
                    {"match": {"domain": "?0"}},
                    {"match": {"customer": "?1"}},
                    {"match": {"datenano": "?2"}}]}}"
                ]
            }
        }
        """)
    Swipe findByDomainAndCustomerAndDateNano(String domain, String customer, String datenano);
}
