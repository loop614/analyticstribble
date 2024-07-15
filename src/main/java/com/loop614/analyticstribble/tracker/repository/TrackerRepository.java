package com.loop614.analyticstribble.tracker.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.loop614.analyticstribble.tracker.entity.Tracker;

interface TrackerRepository extends ElasticsearchRepository<Tracker, String> {}
