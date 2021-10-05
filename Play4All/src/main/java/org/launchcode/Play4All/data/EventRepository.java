package org.launchcode.Play4All.data;

import org.launchcode.Play4All.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
