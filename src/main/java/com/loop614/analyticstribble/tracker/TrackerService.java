package com.loop614.analyticstribble.tracker;

import com.loop614.analyticstribble.tracker.transfer.TrackerFilterTransfer;
import com.loop614.analyticstribble.tracker.transfer.TrackerInputTransfer;

public interface TrackerService {
    boolean save(TrackerInputTransfer trackerInputTransfer);

    public TrackerFilterTransfer getTrackers(TrackerFilterTransfer filterTracker);
}
