package com.loop614.analyticstribble.tracker;

import java.util.ArrayList;

import com.loop614.analyticstribble.tracker.entity.Tracker;
import com.loop614.analyticstribble.tracker.transfer.TrackerFilterTransfer;
import com.loop614.analyticstribble.tracker.transfer.TrackerInputTransfer;
import com.loop614.analyticstribble.tracker.transfer.TrackerTransfer;

public interface TrackerService {
    ArrayList<Tracker> save(TrackerInputTransfer trackerInputTransfer);

    public TrackerTransfer getTrackers(TrackerFilterTransfer filterTracker);
}
