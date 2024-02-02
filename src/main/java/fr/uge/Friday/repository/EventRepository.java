package fr.uge.Friday.repository;

import fr.uge.Friday.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {
}
