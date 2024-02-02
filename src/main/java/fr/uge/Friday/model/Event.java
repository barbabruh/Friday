package fr.uge.Friday.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nameEvent;

    private String furtherInformation; //Par exemple un code de porte, un mot de passe ...

    @Column(nullable = false)
    private String location; //Peut être classe Adresse ?

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private LocalDate eventDay;

    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonFormat(pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime startEvent;

    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonFormat(pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime endEvent;

    public LocalTime getEndEvent() {
        return endEvent;
    }

    public LocalTime getStartEvent() {
        return startEvent;
    }

    public String getLocation() {
        return location;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public int getId() {
        return id;
    }

    public String getFurtherInformation() {
        return furtherInformation;
    }

    public LocalDate getEventDay() {
        return eventDay;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", nameEvent='" + nameEvent + '\'' +
                ", furtherInformation='" + furtherInformation + '\'' +
                ", location=" + location +
                ", startEvent=" + startEvent +
                ", endEvent=" + endEvent +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public void setFurtherInformation(String furtherInformation) {
        this.furtherInformation = furtherInformation;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStartEvent(LocalTime startEvent) {
        this.startEvent = startEvent;
    }

    public void setEventDay(LocalDate eventDay) {
        this.eventDay = eventDay;
    }

    public void setEndEvent(LocalTime endEvent) {
        this.endEvent = endEvent;
    }
}
