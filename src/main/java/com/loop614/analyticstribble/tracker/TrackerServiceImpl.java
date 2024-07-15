package com.loop614.analyticstribble.tracker;

import org.springframework.stereotype.Service;

import com.loop614.analyticstribble.tracker.transfer.TrackerFilterTransfer;
import com.loop614.analyticstribble.tracker.transfer.TrackerInputTransfer;

@Service
public class TrackerServiceImpl implements TrackerService {

    @Override
    public boolean save(TrackerInputTransfer trackerInputTransfer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TrackerFilterTransfer getTrackers(TrackerFilterTransfer filterTracker) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
