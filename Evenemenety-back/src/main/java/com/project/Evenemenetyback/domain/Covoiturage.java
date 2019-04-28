package com.project.Evenemenetyback.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "covoiturage")

public class Covoiturage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "cars_name")
    private String CarsName;

        @Column(name = "price")
    private String price;

    @NotNull
    @Column(name = "meeting_place")
    private String meetingPlace;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarsName() {
        return CarsName;
    }

    public void setCarsName(String carsName) {
        CarsName = carsName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMeetingPlace() {
        return meetingPlace;
    }

    public void setMeetingPlace(String meetingPlace) {
        this.meetingPlace = meetingPlace;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
