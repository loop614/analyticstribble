package com.loop614.analyticstribble.tracker;

import java.util.List;

import com.loop614.analyticstribble.tracker.entity.Tracker;
import com.loop614.analyticstribble.tracker.transfer.TrackerFilterTransfer;
import com.loop614.analyticstribble.tracker.transfer.TrackerInputTransfer;
import com.loop614.analyticstribble.tracker.transfer.TrackerTransfer;

public interface TrackerService {
    List<Tracker> save(TrackerInputTransfer trackerInputTransfer);

    public TrackerTransfer getTrackers(TrackerFilterTransfer filterTracker);
}
