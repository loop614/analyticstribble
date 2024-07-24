package com.loop614.analyticstribble.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.loop614.analyticstribble.tracker.TrackerService;
import com.loop614.analyticstribble.tracker.transfer.TrackerFilterTransfer;
import com.loop614.analyticstribble.tracker.transfer.TrackerInputTransfer;
import com.loop614.analyticstribble.tracker.transfer.TrackerTransfer;

@RestController
public class TrackerController {

    @Autowired
    private final TrackerService trackerService;

    public TrackerController(TrackerService trackerService) {
        this.trackerService = trackerService;
    }

    @PostMapping("/tracker/new")
    public ResponseEntity popularReviewJoinFromReview(@RequestBody TrackerInputTransfer trackerInputTransfer) {
        return new ResponseEntity(this.trackerService.save(trackerInputTransfer), HttpStatus.CREATED);
    }

    @GetMapping("/tracker")
    public ResponseEntity<TrackerTransfer> getTrackers(@ModelAttribute TrackerFilterTransfer filterTracker) {
        return new ResponseEntity<>(this.trackerService.getTrackers(filterTracker), HttpStatus.OK);
    }
}
