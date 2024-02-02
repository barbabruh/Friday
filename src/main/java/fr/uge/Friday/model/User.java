package fr.uge.Friday.model;

import javax.persistence.*;
import java.util.ArrayList;


@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", eventsId=" + eventsId +
                '}';
    }

    public void setId(int id) {
        this.userId = id;
    }

    public int getId() {
        return userId;
    }

    private ArrayList<Integer> eventsId;


    public User(){}

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public ArrayList<Integer> getEventsId() {
        return eventsId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEventsId(ArrayList<Integer> eventsId) {
        this.eventsId = new ArrayList<>();
        this.eventsId.addAll(eventsId);
    }

}
