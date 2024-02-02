package fr.uge.Friday.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.uge.Friday.model.Event;
import fr.uge.Friday.model.User;
import fr.uge.Friday.service.EventService;
import fr.uge.Friday.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @GetMapping("/users/{login}")
    private User bruh(@PathVariable("login") String login){
        return userService.getAllUsers().stream().filter(user -> Objects.equals(user.getLogin(), login)).findFirst().get();
    }

    @GetMapping("/users")
    private List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/user/{userId}")
    private User getUserById(@PathVariable("userId") int userId){
        return userService.getUser(userId);
    }

    @GetMapping("/user/{userId}/event")
    private List<Event> getAllEventOfUser(@PathVariable("userId") int UserId){
        return userService.getAllEventOfUser(UserId);
    }

    @GetMapping("/user/{UserId}/event/{id}")
    private Event getEventOfUserById(@PathVariable("UserId") int UserId, @PathVariable("id") int id){
        return userService.getEventOfUserById(UserId,id);
    }

    @DeleteMapping("/user/{UserId}/event/{id}")
    private void deleteEventOfUserById(@PathVariable("UserId") int UserId, @PathVariable("id") int id){
        User user = userService.getUser(UserId);
        Objects.requireNonNull(user);
        if(user.getEventsId().contains(id)){
            user.getEventsId().remove((Object) id);
            eventService.deleteById(id);
            userService.saveOrUpdate(user);
        }
    }

    @DeleteMapping("/user/{UserId}")
    private void deleteUserAndTheirEvents(@PathVariable("UserId") int UserId){
        User user = userService.getUser(UserId);
        Objects.requireNonNull(user);
        if(!user.getEventsId().isEmpty()){
            user.getEventsId().forEach(id -> eventService.deleteById(id));
        }
        userService.deleteById(UserId);
    }


    @PostMapping("/user")
    private void createUser(@RequestBody String userStr){
        ObjectMapper map = new ObjectMapper();
        try{
            User user = map.readValue(userStr,User.class);
            user.setEventsId(new ArrayList<>());
            userService.saveOrUpdate(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/user/{UserId}/event")
    private void createEventOfUser(@PathVariable("UserId") int UserId, @RequestBody String eventStr){
        ObjectMapper map = new ObjectMapper();
        User user = Objects.requireNonNull(userService.getUser(UserId));
        try{
            Event event = map.readValue(eventStr,Event.class);
            eventService.saveOrUpdate(event);
            user.getEventsId().add(event.getId());
            userService.saveOrUpdate(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/user/{UserId}")
    private void modifyUser(@PathVariable("UserId") int UserId, @RequestBody String userStr){
        ObjectMapper map = new ObjectMapper();
        User user = Objects.requireNonNull(userService.getUser(UserId));
        try{
            User nUser = map.readValue(userStr,User.class);
            nUser.setEventsId(user.getEventsId());
            nUser.setId(UserId);
            userService.saveOrUpdate(nUser);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/user/{UserId}/event/{id}")
    private void modifyEventOfUser(@PathVariable("UserId") int UserId, @PathVariable("id") int id, @RequestBody String eventStr){
        ObjectMapper map = new ObjectMapper();
        User user = Objects.requireNonNull(userService.getUser(UserId));
        if (!user.getEventsId().contains(id)) throw new AssertionError();
        try{
            Event event = map.readValue(eventStr,Event.class);
            event.setId(id);
            eventService.saveOrUpdate(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


}
