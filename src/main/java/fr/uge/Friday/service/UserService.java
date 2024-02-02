package fr.uge.Friday.service;

import fr.uge.Friday.model.Event;
import fr.uge.Friday.model.User;
import fr.uge.Friday.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EventService eventService;

    public List<Event> getAllEventOfUser(int id){
        var t = userRepository.findById(id);
        User user = t.get();
        if(user.getEventsId().isEmpty()){
            return null;
        }
        return eventService.getAllEvent().stream().filter(event -> user.getEventsId().contains(event.getId())).toList();
    }

    public Event getEventOfUserById(int userId, int id){
        User user = userRepository.findById(userId).get();
        Objects.requireNonNull(user);
        Event event = eventService.getEventById(id);
        return user.getEventsId().contains(event.getId()) ? event : null;
    }

    public User getUser(int id){
        return userRepository.findById(id).get();
    }

    public List<User> getAllUsers(){
        ArrayList<User> res = new ArrayList<>();
        userRepository.findAll().forEach(res::add);
        return res;
    }

    public void saveOrUpdate(User user){
        userRepository.save(user);
    }

    public void deleteById(int id){
        userRepository.deleteById(id);
    }

}
