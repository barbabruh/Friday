package fr.uge.Friday.service;

import fr.uge.Friday.model.Event;
import fr.uge.Friday.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    public List<Event> getAllEvent(){
        List<Event> events = new ArrayList<>();
        eventRepository.findAll().forEach(events::add);
        return events;
    }

    public Event getEventById(int id){
        return eventRepository.findById(id).get();
    }

    public void saveOrUpdate(Event event){
        eventRepository.save(event);
    }

    public void deleteById(int id){
        eventRepository.deleteById(id);
    }


}
