package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long, TimeEntry> store = new HashMap<>();
    private long currentId = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = currentId++;

        TimeEntry created = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        store.put(id, created);
        return created;
    }

    @Override
    public TimeEntry find(Long timeEntryId) {
        return store.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(store.values());
    }

    @Override
    public TimeEntry update(Long eq, TimeEntry timeEntry) {


        TimeEntry toUpdate = find(eq);


        if(toUpdate != null) {
          toUpdate.setDate(timeEntry.getDate());
          toUpdate.setHours(timeEntry.getHours());
          toUpdate.setProjectId(timeEntry.getProjectId());
          toUpdate.setUserId(timeEntry.getUserId());

            store.replace(eq, toUpdate);
          return toUpdate;

        } else {return null; }
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

}
